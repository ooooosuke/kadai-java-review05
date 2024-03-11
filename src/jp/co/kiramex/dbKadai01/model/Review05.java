package jp.co.kiramex.dbKadai01.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Review05 {
    public static void main(String[] args) {
        // 3. データベース接続と結果取得のための変数宣言
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. DBと接続する
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "MyOs10281109"
                    );
            // 4. DBとやりとりする窓口（Statementオブジェクト）の作成
            stmt = con.createStatement();
            // 5. 6. Select文の実行と結果を格納／代入
            String sql = "SELECT name, age FROM person WHERE id =1";
            rs = stmt.executeQuery(sql);
            // 7. 結果を表示する
            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println(name);
                System.out.println(age);
            }


        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        } finally {
            // 8. 接続を閉じる
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException e) {
                    System.err.println("ResultSetを閉じるときエラーが発生しました。");
                    e.printStackTrace();
                }
                if(stmt != null ) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        System.err.println("Statementを閉じるときエラーが発生しました。");
                        e.printStackTrace();
                    }
                    if( con != null ){
                        try {
                            con.close();
                        } catch (SQLException e) {
                            System.err.println("データベース切断時にエラーが発生しました。");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
