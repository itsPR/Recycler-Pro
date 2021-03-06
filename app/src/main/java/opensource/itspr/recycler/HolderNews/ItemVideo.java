package opensource.itspr.recycler.HolderNews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import opensource.itspr.recycler.Holders.NewsHolder;
import opensource.itspr.recycler.R;
import opensource.itspr.recycler.Util.customtabs.CustomTabActivityHelper;
import opensource.itspr.recycler.Util.customtabs.WebviewFallback;

/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 13-12-2015.
 */
public class ItemVideo extends NewsHolder {

  private TextView txtView;
  private TextView sourc;
  private ImageView imageView;
  private ImageView playIcon;
  private Activity activityy;
  private ImageView shareIt;

  public ItemVideo(View itemView, Activity activity) {
    super(itemView);
    activityy = activity;
    imageView = (ImageView) itemView.findViewById(R.id.Poster);
    playIcon = (ImageView) itemView.findViewById(R.id.play);
    txtView = (TextView) itemView.findViewById(R.id.Title);
    sourc = (TextView) itemView.findViewById(R.id.source);
    shareIt = (ImageView) itemView.findViewById(R.id.iv_card_share);
  }

  @Override public void bind(String image, String text, Context context) {
    // txtView.setText(text);

    // imageView.setImageResource(image);
  }

  @Override
  public void Feedbind(String Image, String title, String source, String permalink, String date,
      Context context) {

  }

  @Override public void Quotebind(String Image, String title, String author, Context context) {

  }

  @Override public void Imgbind(String Image, Context context) {

  }

  @Override public void Videobind(String Image, String title, String source, final String permalink,
      final Context context) {
    shareIt.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, permalink);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
      }
    });

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setShowTitle(true);
        intentBuilder.setStartAnimations(activityy, R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setExitAnimations(activityy, android.R.anim.slide_in_left,
            android.R.anim.slide_out_right);
        CustomTabActivityHelper.openCustomTab(activityy, intentBuilder.build(),
            Uri.parse(permalink), new WebviewFallback());
      }
    });
    txtView.setText(title);
    sourc.setText(source);
    Drawable drawable1 =
        ContextCompat.getDrawable(context, R.drawable.ic_play_circle_outline_black_24dp);
    Drawable drawable = DrawableCompat.wrap(drawable1);
    DrawableCompat.setTint(drawable, Color.WHITE);
    playIcon.setImageDrawable(drawable);
    ColorDrawable colorDrawable = new ColorDrawable(Color.LTGRAY);
    Glide.with(context)
        .load(Image)
        .centerCrop()
        .placeholder(colorDrawable)
        .crossFade()
        .into(imageView);
  }
}
