package ruanyun.com.meituandemo.util;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import ruanyun.com.meituandemo.R;

public class ImageLoaderOption {
    private static DisplayImageOptions options;

    public static DisplayImageOptions getInstance () {
        if (options == null) {
            options = new DisplayImageOptions.Builder().showImageOnLoading(
                    R.drawable.default_img).showImageOnFail(R.drawable.default_img)
                    // .showImageForEmptyUri(R.drawable.bg_course)
                    .cacheInMemory(true).cacheOnDisk(true).bitmapConfig(
                            Bitmap.Config.RGB_565).build();
        }
        return options;
    }
}
