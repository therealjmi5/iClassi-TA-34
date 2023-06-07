package com.testapp.tugasakhir;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

    public static final String DB_Name = "db_buah.db";
    public static final String TB_Name = "tb_buah";
    public static final String Col = "id";
    public static final String Col1 = "nama";
    public static final String Col2 = "kalori";
    public static final String Col3 = "protein";
    public static final String Col4 = "karbohidrat";
    public static final String Col5 = "air";
    public static final String Col6 = "vitaminc";


    public Database(Context context){super(context, DB_Name, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TB_Name + "(" +
                Col  + " int primary key, " +
                Col1 + " text not null, " +
                Col2 + " text not null, " +
                Col3 + " text not null, " +
                Col4 + " text not null, " +
                Col5 + " text not null, " +
                Col6 + " text not null);";

        sqLiteDatabase.execSQL(sql);
        String[] query =
                {"INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                        ", " + Col5 + ", " + Col6 + ") VALUES (1, 'Pisang', '85', '0.73', '18', '78.3', '9.7')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (2, 'Mangga', '250', '0.36', '6.97', '60', '43')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (3, 'Nanas', '60', '0.46', '14.1', '85', '2.5')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (4, 'Jeruk', '0.14', '0.43', '2', '86.7', '0.068')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (5, 'Kelapa', '354', '3.33', '15.2', '47', '3.3')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (6, 'Manggis', '73', '0.41', '17.9', '80.9', '2.9')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (7, 'Rambutan', '82', '0.65', '20.9', '78', '4.9')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (8, 'Pepaya', '179', '0.39', '0', '43', '0')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (9, 'Markisa', '51', '0.39', '13.6', '85.6', '29.8')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (10, 'Salak', '51', '0.39', '13.6', '85.6', '29.8')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (11, 'Jambu Biji', '68', '2.55', '14.3', '80.8', '228')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (12, 'Kiwi', '0.17', '0.63', '3', '83.9', '0.027')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (13, 'Alpukat', '670', '1.58', '0.06', '160', '81')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (14, 'Semangka', '127', '0.25', '1.21', '30', '3')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (15, 'Leci', '66', '0.83', '16.5', '81.8', '71.5')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (16, 'Delima', '83', '1.67', '18.7', '77.9', '10.2')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (17, 'Jeruk Nipis', '126', '0.3', '33', '30', '0')",

                        "INSERT INTO " + TB_Name + "(" + Col + ", " + Col1 + ", " + Col2 + ", " + Col3 + ", " + Col4 +
                                ", " + Col5 + ", " + Col6 + ") VALUES (18, 'Jagung', '364', '6.2', '80.8', '10.8', '0.13')",
                };

        for (int i = 0; i < query.length ; i++) {
            sqLiteDatabase.execSQL(query[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
