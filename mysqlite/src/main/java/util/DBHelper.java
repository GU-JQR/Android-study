package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * 操作数据库的工具类 SQLite
 * 单例模式 只能被创建一个实例
 * 1. 私有构造方法
 * 2. 对外提供通用入口
 * <p>
 * 继承数据库操作基类  SQLiteOpenHelperv
 * 实现
 * onCreate（） 数据库被创建的时候执行  通常再次方法里面完成数据库的初始化操作（建库建表）
 * onUpgrade（） 数据库被更新的时候执行
 */
public class DBHelper extends SQLiteOpenHelper {

    //本类的实例 对象 声明
    private static DBHelper instance;


    //私有构造方法 四个参数：上下文对象，数据库名称，游标工厂，版本号
    private DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //静态公共方法 获取DBHelper实例，但是确保只能有一个实例
    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            //还没创建实例的时候
            instance = new DBHelper(context, "mystudydb.db", null, 1);
        }
        return instance;
    }


    //数据库创建的时候被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //在mystydtdb数据库下面 创建表
        String sql = "create table if not exists person( _id integer primary key autoincrement not null," +
                "name varchar not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
