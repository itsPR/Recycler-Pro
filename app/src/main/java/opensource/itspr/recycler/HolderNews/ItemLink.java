package opensource.itspr.recycler.HolderNews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import opensource.itspr.recycler.Holders.NewsHolder;
import opensource.itspr.recycler.R;
import opensource.itspr.recycler.Util.customtabs.CustomTabActivityHelper;
import opensource.itspr.recycler.Util.customtabs.WebviewFallback;
import opensource.itspr.recycler.Util.pocket.PocketUtils;

/**
 * Created by Prashanth Reddy (facebook.com/pr.amrita) (github.com/itspr)  on 13-12-2015.
 */
public class ItemLink extends NewsHolder {

  private TextView txtView;
  private ImageView imageView;
  private TextView sourcee;
  private TextView datee;
  private ImageView pocket;
  private Activity activityy;
  private ImageView shareIt;

  public ItemLink(View itemView, Activity activity) {
    super(itemView);
    activityy = activity;

    txtView = (TextView) itemView.findViewById(R.id.txt);
    sourcee = (TextView) itemView.findViewById(R.id.source);
    datee = (TextView) itemView.findViewById(R.id.date);

    imageView = (ImageView) itemView.findViewById(R.id.Poster);
    pocket = (ImageView) itemView.findViewById(R.id.iv_card_pocket);
    shareIt = (ImageView) itemView.findViewById(R.id.iv_card_share);
  }

  @Override public void bind(String image, String text, Context context) {

  }

  @Override public void Feedbind(String Image, String title, String source, final String permalink,
      String date, final Context context) {

    final String permalinkk = "http://" + permalink;
shareIt.setOnClickListener(new View.OnClickListener() {
  @Override public void onClick(View v) {
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, permalinkk);
    sendIntent.setType("text/plain");
    context.startActivity(sendIntent);
  }
});
    pocket.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        if (!PocketUtils.isPocketInstalled(context)) {
          Toast.makeText(activityy, "Pocket is Not Installed", Toast.LENGTH_SHORT).show();
          Log.d("I got clicked", String.valueOf(PocketUtils.isPocketInstalled(context)));
        }
        else {
          PocketUtils.addToPocket(activityy, permalinkk);
        }
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
            Uri.parse(permalinkk), new WebviewFallback());
      }
    });
    ColorDrawable colorDrawable = new ColorDrawable(Color.LTGRAY);
    // Drawable drawable1 = ContextCompat.getDrawable(context, R.drawable.ic_update_black_24dp);
    //Drawable drawable = DrawableCompat.wrap(drawable1);
    //DrawableCompat.setTint(drawable, Color.RED);

    Glide.with(context)
        .load(Image)
        .centerCrop()
        .placeholder(colorDrawable)
        .crossFade()
        .into(imageView);
    txtView.setText(title);
    sourcee.setText(source);
    datee.setText(date);
  }

  @Override public void Quotebind(String Image, String title, String author, Context context) {

  }

  @Override public void Imgbind(String Image, Context context) {

  }

  @Override public void Videobind(String Image, String title, String source, String permalink,
      Context context) {

  }
}
