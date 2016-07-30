package ruanyun.com.meituandemo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class FileUtility {
    private static String TAG = "FileUtility";
    private static final String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String path = SDPATH + "/zhuxin/";
    private static List<String> mediaType = new ArrayList<String>();
    private static List<String> imageType = new ArrayList<String>();


    static {
        imageType.add("jpg");
        imageType.add("jpeg");
        imageType.add("gif");
        imageType.add("png");
    }

    /**
     * 功能描述:获取网络文件
     *
     * @author shengguo 2013-12-18 下午7:03:44
     *
     * @param imageUri
     * @return
     */
    public static File getCacheFile (String imageUri) {
        File cacheFile = null;
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File sdCardDir = Environment.getExternalStorageDirectory();
                String fileName = getFileName(imageUri);
                System.out.println("sdCardDir.getCanonicalPath():" + sdCardDir.getCanonicalPath());
                File dir = new File(sdCardDir.getCanonicalPath()
                /* + AsynImageLoader.CACHE_DIR */);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                cacheFile = new File(dir, fileName);
                Log.i(TAG, "exists:" + cacheFile.exists() + ",dir:" + dir + ",file:" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "getCacheFileError:" + e.getMessage());
        }

        return cacheFile;
    }

    // 删除指定文件夹下所有文件
    // param path 文件夹完整绝对路径
    public static boolean delAllFile (String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }

    // 刪除單個文件
    public static boolean delFile (String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (file.isDirectory()) {
            return flag;
        }
        if (file.isFile()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    // 将bitmap保存为文件
    public static boolean saveBitmap2file (Bitmap bmp, String filename) {
        boolean flag = true;
        String filePath = filename.substring(0, filename.lastIndexOf("/"));
        Log.d(TAG, "-->filePath" + filePath);
        // 如果目标文件已经存在，则删除，产生覆盖旧文件的效果（此处以后可以扩展为已经存在图片不再重新下载功能）
        createFilePath(filePath);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename));
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);// 将图片压缩到流中
            bos.flush();// 输出
            bos.close();// 关闭
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    // 保存拍摄的照片到手机的sd卡
    public static boolean saveBitmap2file2 (Bitmap bitmap, String filename) {
        boolean flag = true;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ByteArrayOutputStream baos = null; // 字节数组输出流
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] byteArray = baos.toByteArray();// 字节数组输出流转换成字节数组
            String saveDir = filename.substring(0, filename.lastIndexOf("/"));
            File dir = new File(saveDir);
            if (!dir.exists()) {
                dir.mkdir(); // 创建文件夹
            }
            File file = new File(filename);
            file.delete();
            if (!file.exists()) {
                file.createNewFile();// 创建文件
                Log.e("PicDir", file.getPath());
            }
            // 将字节数组写入到刚创建的图片文件中
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(byteArray);

        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 功能描述:从全路径中截取文件名
     *
     * @author shengguo 2014-10-22 上午11:44:06
     *
     * @param path
     * @return
     */
    public static String getFileName (String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    // 创建文件夹
    public static void createFilePath (String path) {
        String filepath = path.substring(0, path.lastIndexOf("/"));
        File file = new File(path);
        File filesPath = new File(filepath);
        // 如果目标文件已经存在，则删除，产生覆盖旧文件的效果（此处以后可以扩展为已经存在图片不再重新下载功能）
        Log.d(TAG, "-->path" + path);
        Log.d(TAG, "-->!filesPath.exists()" + !filesPath.exists());
        if (!filesPath.exists()) {
            createFilePath(filepath);
            file.mkdir();
        } else {
            file.mkdir();
        }
    }

    /**
     * 用当前时间给取得的图片命名功能描述:
     *
     * @author shengguo 2013-12-31 上午10:42:27
     *
     * @return
     */
    @SuppressLint ("SimpleDateFormat")
    public static String getFileName () {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random random = new Random();
        int num = random.nextInt() * 100;
        return dateFormat.format(date) + num + ".";
    }

	/*
	 * @SuppressWarnings("resource") public static String getBase64ByFile(File
	 * file) { ByteArrayOutputStream content = new ByteArrayOutputStream();
	 * FileInputStream fStream; try { fStream = new FileInputStream(file);
	 * 设定每次写入1024bytes int bufferSize = 8192; int readBytes = 0; byte[] buffer =
	 * new byte[bufferSize]; // 从文件读取数据到缓冲区 while ((readBytes =
	 * fStream.read(buffer)) != -1) { content.write(buffer, 0, readBytes); }
	 * byte[] bytes = content.toByteArray(); return Base64.encode(bytes); }
	 * catch (IOException e) { e.printStackTrace(); } return null; }
	 */

    /***
     *
     * @Description 通过Base64字符串获取到图片
     * @param imgUrl
     * @return
     * @author Daniel
     */
    public static Bitmap getBase64Image (String imgUrl) {
        byte[] decode = Base64.decode(imgUrl, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        return bitmap;
    }

    /**
     *
     * @param imgPath
     * @param bitmap
     * @param imgFormat 图片格式
     * @return
     */
    public static String imgToBase64 (String imgPath, Bitmap bitmap) {
        if (imgPath != null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        }
        if (bitmap == null) {
            //bitmap not found!!
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static Bitmap readBitmap (String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }

    }

    private static final int TIME_OUT = 10 * 1000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码

    /**
     * android上传文件到服务器
     *
     * @param file
     *            需要上传的文件
     * @param RequestURL
     *            请求的rul
     * @return 返回响应的内容
     */
    public static String uploadFile (File file, String RequestURL) {
        Log.d(TAG, file.getAbsolutePath() + "");
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型

        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("Charset", CHARSET); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

            if (file != null) {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */

                sb.append(
                        "Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                Log.e(TAG, "--->response code:" + res);
                // if(res==200)
                // {
                Log.e(TAG, "--->request success");
                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
                Log.e(TAG, "--->result : " + result);
                return result;
                // }
                // else{
                // Log.e(TAG, "request error");
                // }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 功能描述:获取文件下载路径
     *
     * @author shengguo 2014-2-27 下午9:42:04
     *
     * @return
     */
    public static String getDownloadPath () {
        String path = SDPATH + "/zhuxin/downloads";
        createFilePath(path);
        return path;
    }

    /**
     * 功能描述:获取系统相册路径
     *
     * @author shengguo 2014-2-27 下午9:41:48
     *
     * @return
     */
    public static String getPicturesPath () {
        String path = SDPATH + "/zhuxin/downloads/pictures";
        createFilePath(path);
        return path;
    }

    public static String getPhotoPath () {
        String path = SDPATH + "/zhuxin/photo";
        createFilePath(path);
        return path;
    }

    // /**
    // * 功能描述:获取缩略图路径
    // *
    // * @author shenggguo 2014-12-24 上午10:51:26
    // *
    // * @param fileName
    // * @return
    // */
    // public static String getMiniPicPath(String fileName) {
    // return ApiModule.API_SERVER + "/file/mini/" + fileName;
    // }
    //
    // /**
    // * 功能描述:获取文件或原图路径
    // *
    // * @author shenggguo 2014-12-24 上午10:51:04
    // *
    // * @param fileName
    // * @return
    // */
    // public static String getFileOrPicPath(String fileName) {
    // return ApiModule.API_SERVER + "/file/" + fileName;
    // }

    /**
     * 功能描述:获取缓存的路径
     *
     * @author linrr 2014-2-28 上午9:49:53
     *
     * @return
     */
    public static String getCachePath () {
        String path = SDPATH + "/zhuxin/Cache/";
        createFilePath(path);
        return path;
    }

    /**
     * 功能描述：获取错误日志保存路径
     *
     * @return
     */
    public static String getCrashPath () {
        String path = SDPATH + "/zhuxin/Crash/";
        createFilePath(path);
        return path;
    }

    /**
     * 功能描述:获取录音文件路径
     *
     * @author shenggguo 2014-12-22 下午1:36:44
     *
     * @return
     */
    public static String getVoicePath () {
        String path = SDPATH + "/zhuxin/voice/";
        createFilePath(path);
        return path;
    }

    @SuppressLint ("DefaultLocale")
    public static String getMediaType (File f) {
        String end = f.getName().substring(f.getName().lastIndexOf(".") + 1,
                                           f.getName().length()).toLowerCase();
        String type = "";
        if (mediaType.contains(end)) {
            type = "audio";
        } else if (imageType.contains(end)) {
            type = "image";
        } else {
            type = "*";
        }
        type += "/*";
        return type;
    }

    /**
     * 移动assets文件到缓存 void
     *
     * @exception @since
     *                1.0.0
     */
    public static void WriteToSD (Context context) {
        if (!isExist(context)) {
            write(context);
        }
    }

    private static void write (Context context) {
        InputStream inputStream;
        try {
            inputStream = context.getResources().getAssets().open("ic_launcher.png");
            File file = new File(path, "ic_launcher.png");
            createFilePath(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/ic_launcher.png");
            byte[] buffer = new byte[512];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            System.out.println("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isExist (Context context) {
        File file = new File(path, "ic_launcher.png");
        return file.exists();
    }

    /**
     * 压缩图片
     *
     * @param bmp
     * @param file
     */
    public static void compressBmpToFile (Bitmap bmp, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;// 个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @Description 判断文件是否存在
     * @param mContext
     * @param fileName
     * @return
     * @author Daniel
     */
    public static boolean isFileExist (Context mContext, String fileName) {
        String pathString = mContext.getFilesDir().toString();
        File file = new File(pathString + File.separator + fileName);
        return file.exists();
    }

    /***
     *
     * @Description 通过inputstream，从文件中获取数据
     * @param inputStream
     * @return
     * @author Daniel
     */
    public static String getStringFromFile (InputStream inputStream) {

        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /***
     *
     * @Description 通过文件名，从文件中读取string
     * @param mContext
     * @param fileName
     * @return
     * @author Daniel
     */
    public static String getStringFromFile (Context mContext, String fileName) {
        String pathString = mContext.getFilesDir().toString();
        File file = new File(pathString + File.separator + fileName);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return getStringFromFile(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /***
     *
     * @Description 将json字符串保存到data区的file中
     * @param mContext
     * @param jsonString
     * @param fileName
     * @author Daniel
     */
    public static void saveJsonToFile (Context mContext, String jsonString, String fileName) {
        String pathString = mContext.getFilesDir().toString();
        File filePath = new File(pathString);
        String tableString = pathString + File.separator + fileName;
        File fileTable = new File(tableString);
        try {
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            if (fileTable.exists()) {
                fileTable.delete();
            }
            fileTable.createNewFile();
            FileWriter pw = new FileWriter(fileTable, true);
            pw.write(jsonString);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] File2byte (String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


    /**
     *
     *
     * @Description: file 转byte
     * @author sjj
     * @date 2016-1-27 上午10:51:40
     *
     * @param file
     * @return
     */
    public static byte[] File2byte (File file) {
        byte[] buffer = null;
        try {
            // File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
