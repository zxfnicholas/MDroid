package com.muzhi.mdroid.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


public class ResourceUtils {

	
	public static final String layout="layout";
	public static final String string="string";
	public static final String style="style";
	public static final String anim="anim";
	public static final String attr="attr";
	public static final String raw="raw";
	public static final String id="id";
	public static final String drawable="drawable";
	
	
	/** 根据资源名获取资源id  */
	public static int getIdByName(Context context, String className, String name){
		
		String packageName = context.getPackageName();  
        Class r = null;  
        int id = 0;  
        try {  
            r = Class.forName(packageName + ".R"); 
            Class[] classes = r.getClasses();  
            Class desireClass = null;  
            for (int i = 0; i < classes.length; ++i) {  
                if (classes[i].getName().split("\\$")[1].equals(className)) {  
                    desireClass = classes[i];  
                    break;  
                }  
            } 
            if (desireClass != null)  
                id = desireClass.getField(name).getInt(desireClass);  
        } 
        catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } 
        catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } 
        catch (SecurityException e) {  
            e.printStackTrace();  
        } 
        catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } 
        catch (NoSuchFieldException e) {  
            e.printStackTrace();  
        }  
  
        return id;  
    }
	
	public static int getIdRes(Context context,String name){
		Resources resources = context.getResources();
		int indentify = resources.getIdentifier(context.getPackageName()+":id/"+name, null, null);
		return indentify;
		
	}
	
	
	
	/** 从assets 文件夹中获取文件并读取数据 */
	public static String getTextFromAssets(final Context context, String fileName) {
		String result = "";
		try {
			InputStream in = context.getResources().getAssets().open(fileName);
			// 获取文件的字节数
			int lenght = in.available();
			// 创建byte数组
			byte[] buffer = new byte[lenght];
			// 将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, "UTF-8");
			in.close();
		} catch (Exception e) {
			L.e("Assert:" + fileName);
		}
		return result;
	}

	/** 拷贝资源到sdcard */
	public static boolean copyToSdcard(final Context ctx, String fileName, String target) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = ctx.getAssets().open(fileName);
			out = new FileOutputStream(target);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		} catch (Exception ex) {
			//Logger.e(ex);
			return false;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {

			}
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {

			}
		}
		return true;
	}

	public static Drawable loadImageFromAsserts(final Context ctx, String fileName) {
		try {
			InputStream is = ctx.getResources().getAssets().open(fileName);
			return Drawable.createFromStream(is, null);
		} catch (IOException e) {
			if (e != null) {
				//Logger.e("Assert:" + fileName);
				//Logger.e(e);
			}
		} catch (OutOfMemoryError e) {
			if (e != null) {
				//Logger.e("Assert:" + fileName);
				//Logger.e(e);
			}
		} catch (Exception e) {
			if (e != null) {
				//Logger.e("Assert:" + fileName);
				//Logger.e(e);
			}
		}
		return null;
	}

	/** 从Asset从加载图片 */
	public static void loadImageFromAsserts(final Context ctx, ImageView view, String fileName) {
		try {
			if (ctx != null && ! StringUtils.isEmpty(fileName)) {
				InputStream is = ctx.getResources().getAssets().open(fileName);
				view.setImageDrawable(Drawable.createFromStream(is, null));
			}
		} catch (IOException e) {
			if (e != null) {
				//Logger.e("Assert:" + fileName);
				//Logger.e(e);
			}
		} catch (OutOfMemoryError e) {
			if (e != null) {
				//Logger.e("Assert:" + fileName);
				//Logger.e(e);
			}
		} catch (Exception e) {
			if (e != null) {
				//Logger.e("Assert:" + fileName);
				//Logger.e(e);
			}
		}
	}

	/** 拷贝数据库 */
	public static void copyDatabase(final Context ctx, String dbName) {
		if (ctx != null) {
			File f = ctx.getDatabasePath(dbName);
			if (!f.exists()) {

				// 检测databases文件夹是否已创建
				if (!f.getParentFile().exists())
					f.getParentFile().mkdir();

				try {
					InputStream in = ctx.getAssets().open(dbName);
					OutputStream out = new FileOutputStream(f.getAbsolutePath());

					byte[] buffer = new byte[1024];
					int length;
					while ((length = in.read(buffer)) > 0) {
						out.write(buffer, 0, length);
					}
					in.close();
					out.close();
					//Logger.i("Database copy successed! " + f.getPath());
				} catch (Exception ex) {
					//Logger.e(ex);
				}
			}
		}
	}
}
