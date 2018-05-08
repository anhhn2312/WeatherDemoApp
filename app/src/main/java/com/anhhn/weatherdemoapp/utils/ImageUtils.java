package com.anhhn.weatherdemoapp.utils;

import android.net.Uri;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class ImageUtils {

  public static void loadImage(SimpleDraweeView simpleDraweeView, String imageUrl) {
    ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
            .build();
    simpleDraweeView.setController(
            Fresco.newDraweeControllerBuilder()
                    .setOldController(simpleDraweeView.getController())
                    .setImageRequest(imageRequest)
                    .build());
  }

  public static void loadImage(SimpleDraweeView simpleDraweeView, int imageResId) {
    Uri uri = buildUriWithResource(imageResId);
    ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
            .build();
    simpleDraweeView.setController(
            Fresco.newDraweeControllerBuilder()
                    .setOldController(simpleDraweeView.getController())
                    .setImageRequest(imageRequest)
                    .build());
  }

  public static void loadImage(SimpleDraweeView simpleDraweeView, Object image) {
    if(image instanceof Integer)
      loadImage(simpleDraweeView, (int)image);
    else if(image instanceof String)
      loadImage(simpleDraweeView, (String)image);
  }

  public static void loadImage(SimpleDraweeView simpleDraweeView, Object image, int imageWidth, int imageHeight) {
    if(image instanceof Integer)
      loadImage(simpleDraweeView, (int)image, imageWidth, imageHeight);
    else if(image instanceof String)
      loadImage(simpleDraweeView, (String)image, imageWidth, imageHeight);
  }

  public static void loadImage(SimpleDraweeView simpleDraweeView, String imageUrl, int imageWidth, int imageHeight) {
    ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
            .setResizeOptions(new ResizeOptions(imageWidth, imageHeight))
            .build();
    simpleDraweeView.setController(
            Fresco.newDraweeControllerBuilder()
                    .setOldController(simpleDraweeView.getController())
                    .setImageRequest(imageRequest)
                    .build());
  }

  public static void loadImage(SimpleDraweeView simpleDraweeView, int imageResId, int imageWidth, int imageHeight) {
    Uri uri = buildUriWithResource(imageResId);

    ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
            .setResizeOptions(new ResizeOptions(imageWidth, imageHeight))
            .build();
    simpleDraweeView.setController(
            Fresco.newDraweeControllerBuilder()
                    .setOldController(simpleDraweeView.getController())
                    .setImageRequest(imageRequest)
                    .build());
  }

  public static void loadGifImage(SimpleDraweeView simpleDraweeView, int imageResId){
    DraweeController controller =
            Fresco.newDraweeControllerBuilder()
                    .setUri(buildUriWithResource(imageResId))
                    .setAutoPlayAnimations(true)
                    .build();
    simpleDraweeView.setController(controller);
  }

  private static Uri buildUriWithResource(int resId) {
    return new Uri.Builder()
            .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
            .path(String.valueOf(resId))
            .build();
  }
}
