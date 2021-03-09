package com.a1_1801040189.myfriends.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.a1_1801040189.myfriends.AddFriendActivity;
import com.a1_1801040189.myfriends.EditActivity;
import com.a1_1801040189.myfriends.MainActivity;
import com.a1_1801040189.myfriends.R;
import com.a1_1801040189.myfriends.model.Friend;

import java.util.List;

import static android.content.Intent.getIntent;
import static androidx.core.app.ActivityCompat.startActivityForResult;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendHolder> {

    private List<Friend> friends;
    public static final int Friend_edited=1;

    public FriendsAdapter(List friends) {
        this.friends = friends;

    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //activity
        Context c = parent.getContext();

        //infalter : chage xml to JV object
        LayoutInflater LI = LayoutInflater.from(c);
        View view = LI.inflate(R.layout.friends_list, parent, false);

        return new FriendHolder(view, c);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        Friend f = (Friend) friends.get(position);

        holder.bind(f);
    }

    @Override
    public int getItemCount() {

        return friends.size();
    }

    public class FriendHolder extends RecyclerView.ViewHolder {

        private TextView friendsName;
        private ImageButton call, sms, delete, edit, mail,f5;
        private Context context;

        public FriendHolder(@NonNull View itemView, Context context) {
            super(itemView);

            this.context = context;

            friendsName = itemView.findViewById(R.id.friendsName);

            call = itemView.findViewById(R.id.call);
            sms = itemView.findViewById(R.id.sms);
            delete = itemView.findViewById(R.id.delete);
            mail = itemView.findViewById(R.id.mail);
            edit = itemView.findViewById(R.id.edit);

        }

        public void bind(Friend f) {
            friendsName.setText(f.getName());
            //phone
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:" + f.getPhoneNo()));

                    context.startActivity(i);

                }
            });

            sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent((Intent.ACTION_SENDTO));
                    i.setData(Uri.parse("smsto:" + f.getPhoneNo()));

                    context.startActivity(i);
                }
            });

            mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(Intent.ACTION_SEND);

                    myIntent.setData(Uri.parse("mailto:"+f.getEmail()));
                    myIntent.setType("text/plain");

                    context.startActivity(myIntent);

                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context,EditActivity.class);
                   String uF = i.getStringExtra("edited_f");
                    if(uF==null){
                    int pos = friends.indexOf(f);
                    String p=String.valueOf(pos);
                    i.putExtra("position_edit",p);
                    context.startActivity(i);
                 }



                }
            });




           delete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   new AlertDialog.Builder(context)
                           .setIcon(android.R.drawable.ic_btn_speak_now).setTitle("Delete confirm!")
                           .setMessage("Are you sure to delete!")
                           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   int pos = friends.indexOf(f);
                                   friends.remove(f);
                                   notifyItemRemoved(pos);
                               }
                           }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) { //do nothing
                       }
                   }).show();



               }
           });
        }


    }


}
