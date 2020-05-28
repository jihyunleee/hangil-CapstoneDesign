package com.hausung.hangil.Show;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hausung.hangil.R;

import java.util.ArrayList;

public class RecyclerShowActivity extends RecyclerView.Adapter<RecyclerShowActivity.ViewHolder>{
    private ArrayList<String> mData = null;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView mStrDate;
        TextView mStrTime;
        TextView mStrFinishTime;
        TextView name;
        TextView number;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            id = itemView.findViewById(R.id.id);
            mStrDate = itemView.findViewById(R.id.mStrDate);
            mStrTime = itemView.findViewById(R.id.mStrTime);
            mStrFinishTime = itemView.findViewById(R.id.mStrFinishTime);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    RecyclerShowActivity(ArrayList<String> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerShowActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        RecyclerShowActivity.ViewHolder vh = new RecyclerShowActivity.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerShowActivity.ViewHolder holder, int position) {
        String id = mData.get(0).toString();
        String mStrDate = mData.get(1).toString();
        String mStrTime = mData.get(2).toString();
        String mStrFinishTime = mData.get(3).toString();
        String name = mData.get(4).toString();
        String number = mData.get(5).toString();

        holder.id.setText(id);
        holder.mStrDate.setText(mStrDate);
        holder.mStrTime.setText(mStrTime);
        holder.mStrFinishTime.setText(mStrFinishTime);
        holder.name.setText(name);
        holder.number.setText(number);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}