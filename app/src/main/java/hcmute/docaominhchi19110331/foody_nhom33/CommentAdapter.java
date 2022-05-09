package hcmute.docaominhchi19110331.foody_nhom33;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.docaominhchi19110331.foody_nhom33.Activity.Database;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Comment> commentList;
    Database database;

    public CommentAdapter(Context context, int layout, List<Comment> commentList) {
        this.context = context;
        this.layout = layout;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txt_user,  txt_comment;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CommentAdapter.ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);

            viewHolder = new CommentAdapter.ViewHolder();

            viewHolder.txt_user = (TextView) view.findViewById(R.id.txt_name_user);
            viewHolder.txt_comment = (TextView) view.findViewById(R.id.txt_comment);

            view.setTag(viewHolder);
        } else {
            viewHolder = (CommentAdapter.ViewHolder) view.getTag();
        }

        Comment comment = commentList.get(i);
        viewHolder.txt_user.setText(comment.getName());
        viewHolder.txt_comment.setText(comment.getContent());

        return view;

    }
}
