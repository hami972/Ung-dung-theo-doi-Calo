package com.example.healthcareapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcareapp.CommentActivity;
import com.example.healthcareapp.Fragments.ProfileFragment;
import com.example.healthcareapp.Model.PostInformation;
import com.example.healthcareapp.PostDetailActivity;
import com.example.healthcareapp.R;
import com.example.healthcareapp.service.FcmNotificationsSender;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class CustomAdapter3 extends RecyclerView.Adapter {
    private ArrayList<PostInformation> dataSet;
    Context mContext;
    int total_types;
    FragmentManager fragmentManager;
    FirebaseUser curUser;
    String userToken;


    public static class NoImageType extends RecyclerView.ViewHolder{
        TextView FName, FIngredient, FMaking, FSummary ;
        RatingBar FRating;
        TextView time, username ;
        CircleImageView userimg;
        ImageButton btLike, btComment;
        TextView noOfLikes, noOfComments;
        public NoImageType(View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.username);
            this.userimg = itemView.findViewById(R.id.userimg);
            this.FName = itemView.findViewById(R.id.write);
            this.FRating = itemView.findViewById(R.id.ratingbar);
            this.FIngredient = itemView.findViewById(R.id.write1);
            this.FMaking = itemView.findViewById(R.id.write2);
            this.FSummary = itemView.findViewById(R.id.write3);
            this.time = itemView.findViewById(R.id.time);
            this.btLike = itemView.findViewById(R.id.btn_like);
            this.btComment = itemView.findViewById(R.id.btn_comment);
            this.noOfLikes = itemView.findViewById(R.id.tv_likes_count);
            this.noOfComments = itemView.findViewById(R.id.tv_cmts_count);
        }
    }
    public static class TwoImageType extends RecyclerView.ViewHolder{
        TextView FName, FIngredient, FMaking, FSummary ;
        RatingBar FRating;
        TextView time, username ;
        ImageView img1, img2, img3;
        CircleImageView userimg;
        LinearLayout layout2;
        ImageButton btLike, btComment;
        TextView noOfLikes, noOfComments;
        public TwoImageType(View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.username);
            this.userimg = itemView.findViewById(R.id.userimg);
            this.FName = itemView.findViewById(R.id.write);
            this.FRating = itemView.findViewById(R.id.ratingbar);
            this.FIngredient = itemView.findViewById(R.id.write1);
            this.FMaking = itemView.findViewById(R.id.write2);
            this.FSummary = itemView.findViewById(R.id.write3);
            this.time = itemView.findViewById(R.id.time);
            this.img1 = itemView.findViewById(R.id.img1);
            this.img2 = itemView.findViewById(R.id.img2);
            this.img3 = itemView.findViewById(R.id.img3);
            this.layout2 = itemView.findViewById(R.id.layout2img);
            this.btLike = itemView.findViewById(R.id.btn_like);
            this.btComment = itemView.findViewById(R.id.btn_comment);
            this.noOfLikes = itemView.findViewById(R.id.tv_likes_count);
            this.noOfComments = itemView.findViewById(R.id.tv_cmts_count);
        }
    }
    public static class FourImageType extends RecyclerView.ViewHolder{
        TextView FName, FIngredient, FMaking, FSummary ;
        RatingBar FRating;
        ImageView img1, img2, img3, img4, img5, img6;
        BlurImageView img7;
        TextView time, countimg, username ;
        CircleImageView userimg;
        LinearLayout layout3, layout4;
        ImageButton btLike, btComment;
        TextView noOfLikes, noOfComments;
        public FourImageType(View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.username);
            this.userimg = itemView.findViewById(R.id.userimg);
            this.FName = itemView.findViewById(R.id.write);
            this.FRating = itemView.findViewById(R.id.ratingbar);
            this.FIngredient = itemView.findViewById(R.id.write1);
            this.FMaking = itemView.findViewById(R.id.write2);
            this.FSummary = itemView.findViewById(R.id.write3);
            this.time = itemView.findViewById(R.id.time);
            this.countimg = itemView.findViewById(R.id.countimg);
            this.img1 = itemView.findViewById(R.id.img4);
            this.img2 = itemView.findViewById(R.id.img5);
            this.img3 = itemView.findViewById(R.id.img6);
            this.img4 = itemView.findViewById(R.id.img7);
            this.img5 = itemView.findViewById(R.id.img8);
            this.img6 = itemView.findViewById(R.id.img9);
            this.img7 = itemView.findViewById(R.id.img10);
            this.layout3 = itemView.findViewById(R.id.layout3img);
            this.layout4 = itemView.findViewById(R.id.layout4img);
            this.btLike = itemView.findViewById(R.id.btn_like);
            this.btComment = itemView.findViewById(R.id.btn_comment);
            this.noOfLikes = itemView.findViewById(R.id.tv_likes_count);
            this.noOfComments = itemView.findViewById(R.id.tv_cmts_count);
        }
    }
    public CustomAdapter3(ArrayList<PostInformation> data, Context context, FragmentManager fragmentManager) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
        this.fragmentManager = fragmentManager;
        curUser = FirebaseAuth.getInstance().getCurrentUser();
    }
    @Override
    public int getItemViewType(int position) {


        if(dataSet.get(position).postimgs.size() == 0)
        {
            return 0;
        }
        else if(dataSet.get(position).postimgs.size() < 3)
        {
            return 1;
        }
        else if(dataSet.get(position).postimgs.size() > 2)
        {
            return 2;
        }
        return -1;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int i) {
        View view;
        switch (i) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout0img, parent, false);
                return new NoImageType(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_postitem, parent, false);
                return new TwoImageType(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout4img, parent, false);
                return new FourImageType(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i)  {
        final PostInformation object = dataSet.get(i);
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(object.posttime));
        String pTime = android.text.format.DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
        View.OnClickListener mListener = new View.OnClickListener(){
            public void onClick(View view){
                PostDetailActivity.info = dataSet.get(i);
                mContext.startActivity(new Intent(mContext,PostDetailActivity.class));
            }
        };
        View.OnClickListener userimgListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment fragment = new ProfileFragment();
                Bundle args = new Bundle();
                args.putString("userId", object.userid);
                fragment.setArguments(args);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        };
        View.OnClickListener likeListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference postRef = FirebaseFirestore.getInstance().collection("posts").document(object.id);
                if(!isliked) {
                    postRef.update("likes", FieldValue.arrayUnion(curUser.getUid())).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            object.likes.add(curUser.getUid());
                            if(object.postimgs.size() == 0) {
                                ((NoImageType) viewHolder).btLike.setImageResource(R.drawable.red_heart);
                                ((NoImageType) viewHolder).noOfLikes.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                                ((NoImageType) viewHolder).noOfLikes.setText("" + object.likes.size());
                            } else if(object.postimgs.size() < 3) {
                                ((TwoImageType) viewHolder).btLike.setImageResource(R.drawable.red_heart);
                                ((TwoImageType) viewHolder).noOfLikes.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                                ((TwoImageType) viewHolder).noOfLikes.setText("" + object.likes.size());
                            }
                            else if(object.postimgs.size() > 2) {
                                ((FourImageType) viewHolder).btLike.setImageResource(R.drawable.red_heart);
                                ((FourImageType) viewHolder).noOfLikes.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                                ((FourImageType) viewHolder).noOfLikes.setText("" + object.likes.size());
                            }
                            sendNotification(object.userid);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("Update failed");
                        }
                    });
                }
                else {
                    postRef.update("likes", FieldValue.arrayRemove(curUser.getUid())).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            object.likes.remove(curUser.getUid());
                            if(object.postimgs.size() == 0) {
                                ((NoImageType) viewHolder).btLike.setImageResource(R.drawable.heart);
                                ((NoImageType) viewHolder).noOfLikes.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                                ((NoImageType) viewHolder).noOfLikes.setText("" + object.likes.size());
                            } else if(object.postimgs.size() < 3) {
                                ((TwoImageType) viewHolder).btLike.setImageResource(R.drawable.heart);
                                ((TwoImageType) viewHolder).noOfLikes.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                                ((TwoImageType) viewHolder).noOfLikes.setText("" + object.likes.size());
                            }
                            else if(object.postimgs.size() > 2) {
                                ((FourImageType) viewHolder).btLike.setImageResource(R.drawable.heart);
                                ((FourImageType) viewHolder).noOfLikes.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                                ((FourImageType) viewHolder).noOfLikes.setText("" + object.likes.size());
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("Update failed");
                        }
                    });
                }
                isliked = !isliked;
            }
        };

        View.OnClickListener commentClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("postId", object.id);
                intent.putExtra("authorId", object.userid);
                mContext.startActivity(intent);
            }
        };
        if(object !=null){
            if(object.postimgs.size() == 0)
            {
                ((NoImageType) viewHolder).FName.setText(object.postFoodName);
                ((NoImageType) viewHolder).FRating.setRating(Float.parseFloat(object.postFoodRating));
                ((NoImageType) viewHolder).FIngredient.setText(object.postFoodIngredient);
                ((NoImageType) viewHolder).FMaking.setText(object.postFoodMaking);
                ((NoImageType) viewHolder).FSummary.setText(object.postFoodSummary);
                ((NoImageType) viewHolder).time.setText(pTime);
                ((NoImageType) viewHolder).username.setText(object.username);
                try{
                    Picasso.get().load(object.userimg).into(((NoImageType) viewHolder).userimg);
                }
                catch (Exception e){
                    String uri = "https://i.pinimg.com/originals/0c/3b/3a/0c3b3adb1a7530892e55ef36d3be6cb8.png";
                    Picasso.get().load(uri).into(((NoImageType) viewHolder).userimg);
                }
                ((NoImageType) viewHolder).userimg.setOnClickListener(userimgListener);
                ((NoImageType) viewHolder).username.setOnClickListener(userimgListener);
                ((NoImageType) viewHolder).noOfLikes.setText("" + object.likes.size());
                isLiked(object.id, ((NoImageType) viewHolder).btLike, ((NoImageType) viewHolder).noOfLikes);
                ((NoImageType) viewHolder).btLike.setOnClickListener(likeListener);
                ((NoImageType) viewHolder).noOfComments.setText("" + object.comments.size());
                ((NoImageType) viewHolder).btComment.setOnClickListener(commentClickListener);
            }
            else if(object.postimgs.size() < 3)
            {
                ((TwoImageType) viewHolder).FName.setText(object.postFoodName);
                ((TwoImageType) viewHolder).FRating.setRating(Float.parseFloat(object.postFoodRating));
                ((TwoImageType) viewHolder).FIngredient.setText(object.postFoodIngredient);
                ((TwoImageType) viewHolder).FMaking.setText(object.postFoodMaking);
                ((TwoImageType) viewHolder).FSummary.setText(object.postFoodSummary);
                ((TwoImageType) viewHolder).time.setText(pTime);
                ((TwoImageType) viewHolder).username.setText(object.username);
                ((TwoImageType) viewHolder).noOfLikes.setText("" + object.likes.size());
                ((TwoImageType) viewHolder).noOfComments.setText("" + object.comments.size());
                try{
                    Picasso.get().load(object.userimg).into(((TwoImageType) viewHolder).userimg);
                }
                catch (Exception e){
                    String uri = "https://i.pinimg.com/originals/0c/3b/3a/0c3b3adb1a7530892e55ef36d3be6cb8.png";
                    Picasso.get().load(uri).into(((TwoImageType) viewHolder).userimg);
                }
                ((TwoImageType) viewHolder).userimg.setOnClickListener(userimgListener);
                ((TwoImageType) viewHolder).username.setOnClickListener(userimgListener);
                ((TwoImageType) viewHolder).btLike.setOnClickListener(likeListener);
                ((TwoImageType) viewHolder).btComment.setOnClickListener(commentClickListener);
                if(object.postimgs.size() == 1)
                {
                    Picasso.get().load(object.postimgs.get(0)).into(((TwoImageType) viewHolder).img1);
                    ((TwoImageType) viewHolder).img1.setOnClickListener(mListener);
                    ((TwoImageType) viewHolder).img1.setVisibility(View.VISIBLE);
                    ((TwoImageType) viewHolder).layout2.setVisibility(View.INVISIBLE);
                }
                if(object.postimgs.size() == 2)
                {
                    Picasso.get().load(object.postimgs.get(0)).into(((TwoImageType) viewHolder).img2);
                    Picasso.get().load(object.postimgs.get(1)).into(((TwoImageType) viewHolder).img3);
                    ((TwoImageType) viewHolder).img2.setOnClickListener(mListener);
                    ((TwoImageType) viewHolder).img3.setOnClickListener(mListener);
                    ((TwoImageType) viewHolder).img1.setVisibility(View.INVISIBLE);
                    ((TwoImageType) viewHolder).layout2.setVisibility(View.VISIBLE);
                }

            }
            else if(object.postimgs.size() > 2)
            {
                ((FourImageType) viewHolder).FName.setText(object.postFoodName);
                ((FourImageType) viewHolder).FRating.setRating(Float.parseFloat(object.postFoodRating));
                ((FourImageType) viewHolder).FIngredient.setText(object.postFoodIngredient);
                ((FourImageType) viewHolder).FMaking.setText(object.postFoodMaking);
                ((FourImageType) viewHolder).FSummary.setText(object.postFoodSummary);
                ((FourImageType) viewHolder).time.setText(pTime);
                ((FourImageType) viewHolder).username.setText(object.username);
                ((FourImageType) viewHolder).noOfLikes.setText("" + object.likes.size());
                ((FourImageType) viewHolder).noOfComments.setText("" + object.comments.size());
                try{
                    Picasso.get().load(object.userimg).into(((FourImageType) viewHolder).userimg);
                }
                catch (Exception e){
                    String uri = "https://i.pinimg.com/originals/0c/3b/3a/0c3b3adb1a7530892e55ef36d3be6cb8.png";
                    Picasso.get().load(uri).into(((FourImageType) viewHolder).userimg);
                }
                ((FourImageType) viewHolder).userimg.setOnClickListener(userimgListener);
                ((FourImageType) viewHolder).username.setOnClickListener(userimgListener);
                ((FourImageType) viewHolder).btLike.setOnClickListener(likeListener);
                ((FourImageType) viewHolder).btComment.setOnClickListener(commentClickListener);
                if(object.postimgs.size() == 3)
                {
                    Picasso.get().load(object.postimgs.get(0)).into(((FourImageType) viewHolder).img1);
                    Picasso.get().load(object.postimgs.get(1)).into(((FourImageType) viewHolder).img2);
                    Picasso.get().load(object.postimgs.get(2)).into(((FourImageType) viewHolder).img3);
                    ((FourImageType) viewHolder).img1.setOnClickListener(mListener);
                    ((FourImageType) viewHolder).img2.setOnClickListener(mListener);
                    ((FourImageType) viewHolder).img3.setOnClickListener(mListener);
                    ((FourImageType) viewHolder).layout3.setVisibility(View.VISIBLE);
                    ((FourImageType) viewHolder).layout4.setVisibility(View.INVISIBLE);
                }
                if(object.postimgs.size() >= 4)
                {
                    Picasso.get().load(object.postimgs.get(0)).into(((FourImageType) viewHolder).img4);
                    Picasso.get().load(object.postimgs.get(1)).into(((FourImageType) viewHolder).img5);
                    Picasso.get().load(object.postimgs.get(2)).into(((FourImageType) viewHolder).img6);
                    Picasso.get().load(object.postimgs.get(3)).into(((FourImageType) viewHolder).img7);
                    ((FourImageType) viewHolder).img4.setOnClickListener(mListener);
                    ((FourImageType) viewHolder).img5.setOnClickListener(mListener);
                    ((FourImageType) viewHolder).img6.setOnClickListener(mListener);
                    ((FourImageType) viewHolder).img7.setOnClickListener(mListener);
                    ((FourImageType) viewHolder).countimg.setVisibility(View.INVISIBLE);
                    if(object.postimgs.size() > 4)
                    {
                        Picasso.get().load(object.postimgs.get(3)).into(((FourImageType) viewHolder).img7,new com.squareup.picasso.Callback() {

                            @Override
                            public void onSuccess() {
                                ((FourImageType) viewHolder).img7.setBlur(8);
                            }

                            @Override
                            public void onError(Exception e) {
                            }
                        });

                        ((FourImageType) viewHolder).countimg.setText("+ "+(object.postimgs.size()-3));
                        ((FourImageType) viewHolder).countimg.setVisibility(View.VISIBLE);
                    }
                    ((FourImageType) viewHolder).layout3.setVisibility(View.INVISIBLE);
                    ((FourImageType) viewHolder).layout4.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    private boolean isliked = false;
    private void isLiked(String postId, final ImageButton imageButton, final TextView textView) {
        DocumentReference postRef = FirebaseFirestore.getInstance().collection("posts").document(postId);
        postRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    List<String> likes = (List<String>) documentSnapshot.get("likes");
                    if (likes != null && likes.contains(curUser.getUid())) {
                        imageButton.setImageResource(R.drawable.red_heart);
                        textView.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                        isliked = true;
                    } else {
                        imageButton.setImageResource(R.drawable.heart);
                        textView.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    }

                }
            }
        });
    }
    private void sendNotification(String userId){

        FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userToken = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FcmNotificationsSender notificationsSender = new FcmNotificationsSender(
                        userToken, "Social Food Blog", FirebaseAuth.getInstance().getCurrentUser().getDisplayName() + " liked your post!", mContext
                );
                notificationsSender.sendNotification();
            }
        }, 3000);
    }
}

