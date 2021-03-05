package com.codewithgolap.hindishayari;


import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.widget.EmojiTextView;
import androidx.recyclerview.widget.RecyclerView;


import static android.content.Context.CLIPBOARD_SERVICE;

public class ShayariListAdapter extends RecyclerView.Adapter<ShayariListAdapter.ShayariListViewHolder>{


    EmojiCompat.Config config;

    private String[] data;
    private Context context;
    Dialog dialog;
    ImageButton copy,whatsapp,share;
    public ShayariListAdapter(String[] data,Context context){
        this.data=data;
        this.context=context;

        //we need emoji init first
        config=new BundledEmojiCompatConfig(context);
        EmojiCompat.init(config);

    }
    @Override
    public ShayariListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item,parent,false);

        return new ShayariListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShayariListViewHolder holder, int position) {
        holder.txtTitle.setText(data[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog_layout);
                dialog.show();

                TextView mytxt=dialog.findViewById(R.id.custom_dialog_txtview);
                mytxt.setText(holder.txtTitle.getText().toString());
                copy=dialog.findViewById(R.id.copy);
                share=dialog.findViewById(R.id.share);
                whatsapp=dialog.findViewById(R.id.whatsAppshare);

                // for direct whatsapp share
                whatsapp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent=context.getPackageManager().getLaunchIntentForPackage("com.whatsapp");
//                        context.startActivity(intent);
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.setPackage("com.whatsapp");
                        intent.putExtra(Intent.EXTRA_TEXT, ""+holder.txtTitle.getText());
                        try{
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException ex) {
                            AlertDialog.Builder ab=new AlertDialog.Builder(context);
                            ab.setTitle("Sorry!!");
                            ab.setMessage("WhatsApp is not installed in your device");
                            ab.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            ab.show();

                        }
                    }
                });
                //simple share code
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager myClipboard;
                        myClipboard= (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);

                        ClipData myclip;

                        String text=holder.txtTitle.getText().toString();
                        myclip=ClipData.newPlainText("text",text);
                        myClipboard.setPrimaryClip(myclip);

                        Intent sharingIntent=new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody=text;
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Subject here");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        context.startActivity(Intent.createChooser(sharingIntent,"Share via"));

                    }
                });
                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager myClipboard;
                        myClipboard= (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);

                        ClipData myclip;

                        String text=holder.txtTitle.getText().toString();
                        myclip=ClipData.newPlainText("text",text);
                        myClipboard.setPrimaryClip(myclip);
                        Toast.makeText(context, "कॉपी हो गया ", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }



    public class ShayariListViewHolder extends RecyclerView.ViewHolder{

        EmojiTextView txtTitle;
        public ShayariListViewHolder(View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.shayari_item);
        }
    }
}
