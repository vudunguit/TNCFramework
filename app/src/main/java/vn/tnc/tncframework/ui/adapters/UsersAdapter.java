package vn.tnc.tncframework.ui.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import vn.tnc.core.view.adapters.RecyclerTypeAdapter;
import vn.tnc.data.api.model.response.User;
import vn.tnc.tncframework.R;
import vn.tnc.tncframework.ui.utils.AspectRatioImageView;

/**
 * Created by USER on 5/28/2015.
 */
public class UsersAdapter extends RecyclerTypeAdapter<User, UsersAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;
    private Picasso picasso;
    private OnItemClickListener onItemClickListener;
    @Inject
    public UsersAdapter(LayoutInflater layoutInflater, Picasso picasso){
        this.layoutInflater = layoutInflater;
        this.picasso = picasso;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bind(getItem(position), picasso);
        holder.ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClicked(getItem(position), holder.ivAvatar);
                }
            }
        });
    }

    public interface OnItemClickListener{
        void onItemClicked(User user, ImageView imgView);
    }

    public final class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.ivAvatar)
        AspectRatioImageView ivAvatar;
        @InjectView(R.id.tvLogin)
        TextView tvLogin;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void bind(User user, final Picasso picasso) {
            tvLogin.setText(user.login);
            picasso.load(user.avatar_url).into(new Target() {
                @Override
                public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    float heighRatio = height / (float) width;
                    ivAvatar.setHeightRatio(heighRatio);
                    ivAvatar.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            ivAvatar.getViewTreeObserver().removeOnPreDrawListener(this);
                            //final int height = ivAvatar.getMeasuredHeight();
                            //int width = ivAvatar.getMeasuredWidth();
                            ivAvatar.setImageBitmap(bitmap);
                            return true;
                        }
                    });
                    ivAvatar.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });

            /*
            Transformation transformation = new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    int targetWidth = ivAvatar.getWidth();
                    float aspecRatio = source.getHeight() / (float)source.getWidth();
                    ivAvatar.setHeightRatio(aspecRatio);
                    //int targetHeight = (int)(targetWidth * aspecRatio);
                    int targetHeight = source.getHeight();
                    Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                    if(result != source){
                        //Same bitmap is returned if sizes are the same
                        source.recycle();
                    }
                    return result;
                }

                @Override
                public String key() {
                    return "transformation desiredWidth";
                }
            };
            picasso.load(user.avatar_url).transform(transformation).into(ivAvatar);
            */
        }

    }
}
