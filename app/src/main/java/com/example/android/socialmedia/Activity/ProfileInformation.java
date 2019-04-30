package com.example.android.socialmedia.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.socialmedia.Adapter.ProfileAdapter;
import com.example.android.socialmedia.Adapter.ProfileInformationAdapter;
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

public class ProfileInformation extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Post> post;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private ProfileInformationAdapter profileAdapter;
    private DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    String user_id;

    public ProfileInformation() {

    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);

        SharedPreferences sharedPreferences = getSharedPreferences("ID", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("USER_ID", "");
        post = new ArrayList<>();

        recyclerView = findViewById(R.id.recycle);
        firebaseAuth = FirebaseAuth.getInstance();

        profileAdapter = new ProfileInformationAdapter(post);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setHasFixedSize(true);


        if (firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled( RecyclerView recyclerView, int dx, int dy ) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if (reachedBottom) {

                        loadMorePost();

                    }

                }
            });

            Query firstQuery = firebaseFirestore.collection("Posts").whereEqualTo("user_id", user_id).orderBy("timestamp", Query.Direction.DESCENDING).limit(3);
            firstQuery.addSnapshotListener(ProfileInformation.this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent( QuerySnapshot documentSnapshots, FirebaseFirestoreException e ) {
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

    }

    public void loadMorePost() {

        if (firebaseAuth.getCurrentUser() != null) {

            Query nextQuery = firebaseFirestore.collection("Posts").whereEqualTo("user_id", user_id).orderBy("timestamp", Query.Direction.DESCENDING).startAfter(lastVisible).limit(3);

            nextQuery.addSnapshotListener(ProfileInformation.this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent( QuerySnapshot documentSnapshots, FirebaseFirestoreException e ) {
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
