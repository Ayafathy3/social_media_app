package com.example.android.socialmedia.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.socialmedia.Activity.CommentsActivity;
import com.example.android.socialmedia.R;
import com.example.android.socialmedia.classes.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    ArrayList<Post> arrayList;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    class profileData extends RecyclerView.ViewHolder {
        TextView nameProdile, fieldProfile;
        ImageView profileImage;
        private View mView;

        public profileData( View itemView ) {
            super(itemView);
            this.mView = itemView;
            nameProdile = mView.findViewById(R.id.profile_name);
            profileImage = mView.findViewById(R.id.profile_image);
            fieldProfile = mView.findViewById(R.id.profile_field);

        }

    }

    class profilePosts extends RecyclerView.ViewHolder {
        private View mView;
        private TextView descView;
        private ImageView blogImageView;
        private TextView blogDate;

        private TextView blogUserName;
        private CircleImageView blogUserImage;

        private ImageView blogLikeBtn;
        private TextView blogLikeCount;

        private ImageView blogCommentBtn;

        public profilePosts( View itemView ) {
            super(itemView);
            mView = itemView;

            blogLikeBtn = mView.findViewById(R.id.blog_like_btn);
            blogCommentBtn = mView.findViewById(R.id.blog_comment_icon);

        }

        public void setDescText( String descText ) {

            descView = mView.findViewById(R.id.blog_desc);
            descView.setText(descText);

        }

        public void setBlogImage( String downloadUri, String thumbUri ) {

            blogImageView = mView.findViewById(R.id.blog_image);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.image_placeholder);

            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(downloadUri).thumbnail(Glide.with(context).load(thumbUri)).into(blogImageView);

        }

        public void setTime( String date ) {

            blogDate = mView.findViewById(R.id.blog_date);
            blogDate.setText(date);

        }

        public void setUserData( String name, String image ) {

            blogUserImage = mView.findViewById(R.id.blog_user_image);
            blogUserName = mView.findViewById(R.id.blog_user_name);

            blogUserName.setText(name);

            RequestOptions placeholderOption = new RequestOptions();
            placeholderOption.placeholder(R.drawable.profile_placeholder);

            Glide.with(context).applyDefaultRequestOptions(placeholderOption).load(image).into(blogUserImage);

        }

        public void updateLikesCount( int count ) {

            blogLikeCount = mView.findViewById(R.id.blog_like_count);
            blogLikeCount.setText(count + " Likes");

        }

    }

    public ProfileAdapter() {
    }

    public ProfileAdapter( ArrayList<Post> arrayList ) {
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        this.context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.profile_item_user, parent, false);
            return new profileData(view);
        } else if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
            return new profilePosts(view);
        } else {
            return null;
        }

    }

    @Override
    public void onBindViewHolder( @NonNull RecyclerView.ViewHolder viewHolder1, int position ) {
        if (viewHolder1.getItemViewType() == 0) {
            final profileData viewHolder = (profileData) viewHolder1;
            viewHolder.setIsRecyclable(false);

            String user_id = firebaseAuth.getCurrentUser().getUid();
            firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete( @NonNull Task<DocumentSnapshot> task ) {

                    if (task.isSuccessful()) {
                        String field = task.getResult().getString("field");
                        String userName = task.getResult().getString("name");
                        String userImage = task.getResult().getString("image");
                        viewHolder.nameProdile.setText(userName);
                        viewHolder.fieldProfile.setText("interested in " + field);

                        RequestOptions placeholderOption = new RequestOptions();
                        placeholderOption.placeholder(R.drawable.user_image);

                        Glide.with(context).applyDefaultRequestOptions(placeholderOption).load(userImage).into(viewHolder.profileImage);


                    } else {

                        //Firebase Exception

                    }

                }
            });

        } else if (viewHolder1.getItemViewType() == 1) {

            final profilePosts holder1 = (profilePosts) viewHolder1;
            holder1.setIsRecyclable(false);

            final String blogPostId = arrayList.get(position).BlogPostId;
            final String currentUserId = firebaseAuth.getCurrentUser().getUid();

            String desc_data = arrayList.get(position).getDesc();
            holder1.setDescText(desc_data);

            String image_url = arrayList.get(position).getImage_url();
            String thumbUri = arrayList.get(position).getImage_thumb();
            holder1.setBlogImage(image_url, thumbUri);

            //User Data will be retrieved here...
            firebaseFirestore.collection("Users").document(currentUserId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete( @NonNull Task<DocumentSnapshot> task ) {

                    if (task.isSuccessful()) {

                        String userName = task.getResult().getString("name");
                        String userImage = task.getResult().getString("image");

                        holder1.setUserData(userName, userImage);


                    } else {

                        //Firebase Exception

                    }

                }
            });
            try {
                long millisecond = arrayList.get(position).getTimestamp().getTime();
                String dateString = DateFormat.format("MM/dd/yyyy", new Date(millisecond)).toString();
                holder1.setTime(dateString);
            } catch (Exception e) {

                //  Toast.makeText(context, "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            //Get Likes Count
            firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent( QuerySnapshot documentSnapshots, FirebaseFirestoreException e ) {
                    try {
                        if (!documentSnapshots.isEmpty()) {

                            int count = documentSnapshots.size();

                            holder1.updateLikesCount(count);

                        } else {

                            holder1.updateLikesCount(0);

                        }

                    } catch (Exception i) {

                    }
                }
            });


            //Get Likes
            firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").document(currentUserId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent( DocumentSnapshot documentSnapshot, FirebaseFirestoreException e ) {
                    try {


                        if (documentSnapshot.exists()) {

                            holder1.blogLikeBtn.setImageDrawable(context.getDrawable(R.mipmap.action_like_accent));

                        } else {

                            holder1.blogLikeBtn.setImageDrawable(context.getDrawable(R.mipmap.action_like_gray));

                        }

                    } catch (Exception e1) {


                    }
                }
            });

            //Likes Feature
            holder1.blogLikeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick( View v ) {

                    firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").document(currentUserId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete( @NonNull Task<DocumentSnapshot> task ) {

                            if (!task.getResult().exists()) {

                                Map<String, Object> likesMap = new HashMap<>();
                                likesMap.put("timestamp", FieldValue.serverTimestamp());

                                firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").document(currentUserId).set(likesMap);

                            } else {

                                firebaseFirestore.collection("Posts/" + blogPostId + "/Likes").document(currentUserId).delete();

                            }

                        }
                    });
                }
            });

            holder1.blogCommentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick( View v ) {

                    Intent commentIntent = new Intent(context, CommentsActivity.class);
                    context.startActivity(commentIntent);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType( int position ) {
        if (position == 0) return 0;
        else return 1;
    }
}
