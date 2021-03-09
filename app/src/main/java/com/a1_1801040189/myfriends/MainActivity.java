package com.a1_1801040189.myfriends;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.a1_1801040189.myfriends.adapters.FriendsAdapter;
import com.a1_1801040189.myfriends.model.Friend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton Add_btn;
    public static final int Friend_added=1;

    public List<Friend> list = new ArrayList<>() ;
    private FriendsAdapter friendsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list.add(new Friend("tien","tiendinh@gmail","123445"));
        list.add(new Friend("tien1","tiendinh1@gmail","1234453"));
        list.add(new Friend("tien2","tiendinh2@gmail","12344455"));
        list.add(new Friend("tien3","tiendinh4@gmail","1234445"));


        RecyclerView rvFriends = findViewById(R.id.rvFriends);
        Add_btn = findViewById(R.id.Addbtn);


        friendsAdapter=new FriendsAdapter(list);
        rvFriends.setAdapter(friendsAdapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(this));

  /// Update part
        Intent input= getIntent();
        Friend uf = (Friend) input.getSerializableExtra("edited_f");
        String p = input.getStringExtra("pos");
       if(uf!=null && p!=null){
        int pos= Integer.parseInt(p);
        list.set(0,uf);
        friendsAdapter.notifyDataSetChanged();}

        Add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddFriendActivity.class);
                startActivityForResult(i,Friend_added);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK &&  requestCode==Friend_added){
           Friend f= (Friend) data.getSerializableExtra("f");

           list.add(0,f);

           friendsAdapter.notifyItemInserted(0);

        }


    }
}