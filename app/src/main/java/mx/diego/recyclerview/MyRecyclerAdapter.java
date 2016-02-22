package mx.diego.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    public static final int LINEAR = 1;
    public static final int GRID = 2;
    private List<ItemClass> elements;
    private AdapterListener listener;
    private int layout;

    public MyRecyclerAdapter(List<ItemClass> elements, AdapterListener listener) {
        this.elements = elements;
        this.listener = listener;
        setLayoutType(LINEAR);
    }

    public void setLayoutType(int type) {
        if(type == LINEAR) {
            layout = R.layout.row_view;
        } else {
            layout = R.layout.row_grid;
        }
    }

    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MyViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, int position) {
        holder.title.setText(elements.get(position).getTitle());
        holder.subtitle.setText(elements.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition(), elements.get(getAdapterPosition()));
                }
            });

            title = (TextView) itemView.findViewById(R.id.row_title);
            subtitle = (TextView) itemView.findViewById(R.id.row_subtitle);
            icon = (ImageView) itemView.findViewById(R.id.row_icon);
        }
    }

    interface AdapterListener {
        void onClick(int position, ItemClass item);
    }
}
