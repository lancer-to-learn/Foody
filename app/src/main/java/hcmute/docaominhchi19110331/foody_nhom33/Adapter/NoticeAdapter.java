package hcmute.docaominhchi19110331.foody_nhom33.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.docaominhchi19110331.foody_nhom33.Food;
import hcmute.docaominhchi19110331.foody_nhom33.FoodAdapter;
import hcmute.docaominhchi19110331.foody_nhom33.Model.Notice;
import hcmute.docaominhchi19110331.foody_nhom33.R;

public class NoticeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Notice> noticeList;

    public NoticeAdapter(Context context, int layout, List<Notice> noticeList) {
        this.context = context;
        this.layout = layout;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
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
        TextView txt_notice,  txt_time;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        NoticeAdapter.ViewHolder viewHolder;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);

            viewHolder = new NoticeAdapter.ViewHolder();

            viewHolder.txt_notice = (TextView) view.findViewById(R.id.txt_notice);
            viewHolder.txt_time = (TextView) view.findViewById(R.id.txt_time);

            view.setTag(viewHolder);
        } else {
            viewHolder = (NoticeAdapter.ViewHolder) view.getTag();
        }

        Notice notice = noticeList.get(i);
        viewHolder.txt_notice.setText(notice.getContent());
        viewHolder.txt_time.setText(notice.getTime().toString());

        return view;

    }
}
