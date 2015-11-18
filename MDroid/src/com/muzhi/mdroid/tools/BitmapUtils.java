package com.muzhi.mdroid.tools;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Bitmap.CompressFormat;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件操作类
 */
public class BitmapUtils {

	
	/**
	 * 获取图片Bitmap(缩小一倍)
	 * @param filePath
	 * @return
	 */
	public static Bitmap getBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = 2;
		options.inJustDecodeBounds = false;
		Bitmap bm = BitmapFactory.decodeFile(filePath, options);
		if(bm == null){
			return  null;
		}
		int degree = readPictureDegree(filePath);
		bm = rotateBitmap(bm,degree) ;
		
		return bm;
	}
	
	private static int readPictureDegree(String path) {	
       int degree  = 0;  
       try {  
           ExifInterface exifInterface = new ExifInterface(path);  
           int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);  
           switch (orientation) {  
	           case ExifInterface.ORIENTATION_ROTATE_90: 
	        	   degree = 90;  
	               break;  
	           case ExifInterface.ORIENTATION_ROTATE_180:  
	               degree = 180;  
	               break;  
	           case ExifInterface.ORIENTATION_ROTATE_270:  
	               degree = 270;  
	               break;  
           } 
       }
       catch (IOException e) {  
           e.printStackTrace();  
       }
       return degree;  
	}
	private static Bitmap rotateBitmap(Bitmap bitmap, int rotate){
		if(bitmap == null)
			return null ;
		
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		// Setting post rotate to 90
		Matrix mtx = new Matrix();
		mtx.postRotate(rotate);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
	}
	
	
	/**
	 * 保存图片并返回文件路径
	 * @param bitmap
	 * @param folderName
	 * @param fileName 为空则随机生成
	 */
	public static String saveAsBitmap(Context context,Bitmap bitmap){
		
		String folderName=AppUtils.getAppName(context);
		if(folderName==null || folderName.equals("")){
			folderName="CameraSDK";
		}
		
		File parentpath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		String fileName = System.currentTimeMillis() + ".jpg";//随机生成一个名称
		fileName=folderName+"/" + fileName;		
		
		File file = new File(parentpath,fileName);
		file.getParentFile().mkdirs();
		try {
			bitmap.compress(CompressFormat.JPEG, 100, new FileOutputStream(file));
			MediaScannerConnection.scanFile(context, new String[] { file.toString() }, null, null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/*if (file.exists()) {
			Filter_LocalPhotoInfo mLocalPhotoInfo = new Filter_LocalPhotoInfo(file.getAbsolutePath(), "file://"+ file.getAbsolutePath());
			
		} else {
			Toast.makeText(getActivity(), "获取图片失败", Toast.LENGTH_SHORT).show();
		}*/
		bitmap.recycle();
		return file.getAbsolutePath();
	}
	
	
	
	
	
	/**
	 * 图片旋转
	 * @param bit
	 * 旋转原图像
	 * 
	 * @param degrees
	 * 旋转度数
	 * 
	 * @return
	 * 旋转之后的图像
	 * 
	 */
	public static Bitmap rotateImage(Bitmap bit, int degrees){
		Matrix matrix = new Matrix();
		matrix.postRotate(degrees);
		Bitmap tempBitmap = Bitmap.createBitmap(bit, 0, 0, bit.getWidth(),bit.getHeight(), matrix, true);
		return tempBitmap;
	}
	
	/**
	 * 翻转图像
	 * 
	 * @param bit
	 * 翻转原图像
	 * 
	 * @param x
	 * 翻转X轴
	 * 
	 * @param y
	 * 翻转Y轴
	 * 
	 * @return
	 * 翻转之后的图像
	 * 
	 * 说明:
	 * (1,-1)上下翻转
	 * (-1,1)左右翻转
	 * 
	 */
	public static Bitmap reverseImage(Bitmap bit,int x,int y){
		Matrix matrix = new Matrix();
		matrix.postScale(x, y);		
		Bitmap tempBitmap = Bitmap.createBitmap(bit, 0, 0, bit.getWidth(),bit.getHeight(), matrix, true);
		return tempBitmap;
	}
		
	
	public static Bitmap ResizeBitmap(Bitmap bitmap, int scale){
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.postScale(1/scale, 1/scale);

		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,matrix, true);
		bitmap.recycle();
		return resizedBitmap;
	}
	

	
	 /**
     * 获取一个指定大小的bitmap
     *
     * @param res       Resources
     * @param resId     图片ID
     * @param reqWidth  目标宽度
     * @param reqHeight 目标高度
     */
    public static Bitmap bitmapFromResource(Resources res, int resId,int reqWidth, int reqHeight) {
        // BitmapFactory.Options options = new BitmapFactory.Options();
        // options.inJustDecodeBounds = true;
        // BitmapFactory.decodeResource(res, resId, options);
        // options = BitmapHelper.calculateInSampleSize(options, reqWidth,
        // reqHeight);
        // return BitmapFactory.decodeResource(res, resId, options);

        // 通过JNI的形式读取本地图片达到节省内存的目的
        InputStream is = res.openRawResource(resId);
        return bitmapFromStream(is, null, reqWidth, reqHeight);
    }

    /**
     * 获取一个指定大小的bitmap
     *
     * @param reqWidth  目标宽度
     * @param reqHeight 目标高度
     */
    public static Bitmap bitmapFromFile(String pathName, int reqWidth,int reqHeight) {
        if (reqHeight == 0 || reqWidth == 0) {
            try {
                return BitmapFactory.decodeFile(pathName);
            } 
            catch (OutOfMemoryError e) {
            }
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options = calculateInSampleSize(options, reqWidth, reqHeight);
        return BitmapFactory.decodeFile(pathName, options);
    }

    /**
     * 获取一个指定大小的bitmap
     *
     * @param data      Bitmap的byte数组
     * @param offset    image从byte数组创建的起始位置
     * @param length    the number of bytes, 从offset处开始的长度
     * @param reqWidth  目标宽度
     * @param reqHeight 目标高度
     */
    public static Bitmap bitmapFromByteArray(byte[] data, int offset,
                                             int length, int reqWidth, int reqHeight) {
        if (reqHeight == 0 || reqWidth == 0) {
            try {
                return BitmapFactory.decodeByteArray(data, offset, length);
            } catch (OutOfMemoryError e) {
            }
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeByteArray(data, offset, length, options);
        options = calculateInSampleSize(options, reqWidth, reqHeight);
        return BitmapFactory.decodeByteArray(data, offset, length, options);
    }

    /**
     * 获取一个指定大小的bitmap<br>
     * 实际调用的方法是bitmapFromByteArray(data, 0, data.length, w, h);
     *
     * @param is        从输入流中读取Bitmap
     * @param reqWidth  目标宽度
     * @param reqHeight 目标高度
     */
    /*public static Bitmap bitmapFromStream(InputStream is, int reqWidth,int reqHeight) {
        if (reqHeight == 0 || reqWidth == 0) {
            try {
                return BitmapFactory.decodeStream(is);
            } catch (OutOfMemoryError e) {
            }
        }
        byte[] data = FileUtils.input2byte(is);
        return bitmapFromByteArray(data, 0, data.length, reqWidth, reqHeight);
    }*/

    /**
     * 获取一个指定大小的bitmap
     *
     * @param is         从输入流中读取Bitmap
     * @param outPadding If not null, return the padding rect for the bitmap if it
     *                   exists, otherwise set padding to [-1,-1,-1,-1]. If no bitmap
     *                   is returned (null) then padding is unchanged.
     * @param reqWidth   目标宽度
     * @param reqHeight  目标高度
     */
    public static Bitmap bitmapFromStream(InputStream is, Rect outPadding,int reqWidth, int reqHeight) {
        Bitmap bmp = null;
        if (reqHeight == 0 || reqWidth == 0) {
            try {
                return BitmapFactory.decodeStream(is);
            } catch (OutOfMemoryError e) {
            }
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeStream(is, outPadding, options);
        options = calculateInSampleSize(options, reqWidth, reqHeight);
        bmp = BitmapFactory.decodeStream(is, outPadding, options);
        return bmp;
    }

    /**
     * 图片压缩处理（使用Options的方法）
     * <br>
     * <b>说明</b> 使用方法：
     * 首先你要将Options的inJustDecodeBounds属性设置为true，BitmapFactory.decode一次图片 。
     * 然后将Options连同期望的宽度和高度一起传递到到本方法中。
     * 之后再使用本方法的返回值做参数调用BitmapFactory.decode创建图片。
     * <br>
     * <b>说明</b> BitmapFactory创建bitmap会尝试为已经构建的bitmap分配内存
     * ，这时就会很容易导致OOM出现。为此每一种创建方法都提供了一个可选的Options参数
     * ，将这个参数的inJustDecodeBounds属性设置为true就可以让解析方法禁止为bitmap分配内存
     * ，返回值也不再是一个Bitmap对象， 而是null。虽然Bitmap是null了，但是Options的outWidth、
     * outHeight和outMimeType属性都会被赋值。
     *
     * @param reqWidth  目标宽度,这里的宽高只是阀值，实际显示的图片将小于等于这个值
     * @param reqHeight 目标高度,这里的宽高只是阀值，实际显示的图片将小于等于这个值
     */
    public static BitmapFactory.Options calculateInSampleSize(
            final BitmapFactory.Options options, final int reqWidth,
            final int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        // 设置压缩比例
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return options;
    }
}
