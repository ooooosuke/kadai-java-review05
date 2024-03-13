package jp.co.kiramex.dbKadai01.model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Review05 {
    public static void main(String[] args) {
        // 3. データベース接続と結果取得のための変数宣言
        Connection con = null;
        PreparedStatement pstmt = null;
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
            String sql = "SELECT * FROM person WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            // 5, 6. Select文の実行と結果を格納／代入
            System.out.print("検索キーワードを入力してください > ");
            String input = keyIn();

            // PreparedStatementオブジェクトの?に値をセット
            int id = Integer.parseInt(input);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
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
                if(pstmt != null ) {
                    try {
                        pstmt.close();
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
    private static String keyIn() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        } catch (IOException e) {

        }
        return line;
    }
}


