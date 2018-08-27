package im.vector.rios;

import android.view.View;
import android.widget.TextView;

import im.vector.R;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * Created by tlh on 2016/10/1 :)
 */

public class FileNodeBinder extends TreeViewBinder<FileNodeBinder.ViewHolder> {
    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node) {
        RFile fileNode = (RFile) node.getContent();
        holder.tvName.setText(fileNode.fileName);
    }

    @Override
    public int getLayoutId() {
        return R.layout.rios_item_file;
    }

    public class ViewHolder extends TreeViewBinder.ViewHolder {
        public TextView tvName;

        public ViewHolder(View rootView) {
            super(rootView);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }

    }
}
