package vn.tnc.tncframework.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import vn.tnc.core.view.adapters.RecyclerTypeAdapter;
import vn.tnc.data.api.model.response.User;
import vn.tnc.tncframework.R;

/**
 * Created by USER on 5/28/2015.
 */
public class UsersAdapter extends RecyclerTypeAdapter<User, UsersAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;
    private Picasso picasso;
    @Inject
    public UsersAdapter(LayoutInflater layoutInflater, Picasso picasso){
        this.layoutInflater = layoutInflater;
        this.picasso = picasso;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_user, parent, false), picasso);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public final class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.ivAvatar)
        ImageView ivAvatar;
        @InjectView(R.id.tvLogin)
        TextView tvLogin;

        private Picasso picasso;
        public ViewHolder(View itemView, Picasso picasso) {
            super(itemView);
            this.picasso = picasso;
            ButterKnife.inject(this, itemView);
        }

        public void bind(User user) {
            tvLogin.setText(user.login);
            picasso.load(user.avatar_url).placeholder(R.drawable.ic_avatar_default).error(R.drawable.ic_avatar_default).into(ivAvatar);
        }

    }
}
