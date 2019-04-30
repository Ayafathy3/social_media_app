package com.example.android.socialmedia.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.socialmedia.Adapter.PostAdapter;
import com.example.android.socialmedia.Adapter.ProfileAdapter;
import com.example.android.socialmedia.R;
import com.example.android.socialmedia.classes.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ProfileFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Post> post;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private ProfileAdapter profileAdapter;
    public String current_user_id;

    private DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        post = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycle);

        firebaseAuth = FirebaseAuth.getInstance();
        current_user_id = firebaseAuth.getCurrentUser().getUid();
        profileAdapter = new ProfileAdapter(post);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setHasFixedSize(true);

        if (firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if (reachedBottom) {

                        loadMorePost();

                    }

                }
            });

            Query firstQuery = firebaseFirestore.collection("Posts").whereEqualTo("user_id", current_user_id).orderBy("timestamp", Query.Direction.DESCENDING).limit(3);
            firstQuery.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    try {


                        if (!documentSnapshots.isEmpty()) {

                            if (isFirstPageFirstLoad) {

                                lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                                post.clear();

                            }

                            for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                                if (doc.getType() == DocumentChange.Type.ADDED) {

                                    String blogPostId = doc.getDocument().getId();
                                    Post blogPost = doc.getDocument().toObject(Post.class).withId(blogPostId);

                                    if (isFirstPageFirstLoad) {

                                        post.add(blogPost);

                                    } else {

                                        post.add(0, blogPost);

                                    }


                                    profileAdapter.notifyDataSetChanged();

                                }
                            }

                            isFirstPageFirstLoad = false;

                        }
                    } catch (Exception i) {

                    }
                }

            });

        }

        // Inflate the layout for this fragment
        return view;
    }

    public void loadMorePost() {

        if (firebaseAuth.getCurrentUser() != null) {

            Query nextQuery = firebaseFirestore.collection("Posts").whereEqualTo("user_id", current_user_id).orderBy("timestamp", Query.Direction.DESCENDING).startAfter(lastVisible).limit(3);

            nextQuery.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    try {


                        if (!documentSnapshots.isEmpty()) {

                            lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                            for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                                if (doc.getType() == DocumentChange.Type.ADDED) {

                                    String blogPostId = doc.getDocument().getId();
                                    Post blogPost = doc.getDocument().toObject(Post.class).withId(blogPostId);
                                    post.add(blogPost);

                                    profileAdapter.notifyDataSetChanged();
                                }

                            }
                        }
                    } catch (Exception y) {

                    }
                }
            });

        }

    }


}
