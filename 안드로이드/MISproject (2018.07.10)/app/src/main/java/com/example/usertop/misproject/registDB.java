package com.example.usertop.misproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class registDB extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg ="";
    String [] recMsg;
    URL url;
    int select=0;
    int count;
    // doInBackground의 매개값이 문자열 배열인데요. 보낼 값이 여러개일 경우를 위해 배열로 합니다.

    @Override
    protected String doInBackground(String[] strings) {
        try {
            if(strings[0].equals("pwdset")) { // 비밀번호 설정

                //url = new URL("http://220.66.144.102:8282/project/product/getauthority.jsp"); //정훈컴
                url = new URL("http://121.183.170.102:8181/project/product/getauthority.jsp"); // 블루베리컴
                //url = new URL("http://220.66.144.32:8181/project1/product/getauthority.jsp"); //서버컴
                sendMsg = "enumber=" + strings[1] + "&password=" + strings[2];
                select = 1;
                System.out.println("sendMsg: " + sendMsg);
            }else if(strings[0].equals("idcheck")){ // id 확인

                //url = new URL("http://220.66.144.102:8282/project/product/idcheck.jsp");
                url = new URL("http://121.183.170.102:8181/project/product/idcheck.jsp");
                //url = new URL("http://220.66.144.32:8181/project1/product/idcheck.jsp");
                sendMsg = "enumber=" + strings[1];
                select = 2;
            }else if(strings[0].equals("authority")){ // 로그인 정보 받아오기

                //url = new URL("http://220.66.144.102:8282/project/product/memberinfosubmit.jsp");
                url = new URL("http://121.183.170.102:8181/project/product/memberinfosubmit.jsp");
                //url = new URL("http://220.66.144.32:8181/project1/product/memberinfosubmit.jsp");
                sendMsg = "enumber=" + strings[1] + "&password=" + strings[2];
                select = 3;
            }
            else if(strings[0].equals("depcode")){
                url = new URL("http://121.183.170.102:8181/project/product/filedownload.jsp");
                sendMsg = "depcode=" + strings[1];
                select = 4;
            }

            /*서버연결 */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            //회원가입처럼 보낼 데이터가 여러 개일 경우 &로 구분하여 작성합니다.
            osw.write(sendMsg); // OutputStreamWriter에 담아 전송합니다.
            osw.flush();

            // jsp와 통신이 정상적으로 되었을 때 할 코드들입니다.
            if(conn.getResponseCode() == conn.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                String line = null;
                String page = "";

                //jsp에서 보낸 값 받기
                while ((line = br.readLine()) != null) {
                    //buffer.append(str);
                    page += line;
                }

                if(select == 1){
                    return "";
                }

                JSONObject json = new JSONObject(page);
                JSONArray jArr = json.getJSONArray("dataSend");

                if(select==2) {
                    for (int i = 0; i < jArr.length(); i++) {
                        json = jArr.getJSONObject(i);
                        receiveMsg = json.getString("Msg1");
                        System.out.println("Idcheck: "+receiveMsg);
                    }
                }else if(select==3) {
                    for (int i = 0; i < jArr.length(); i++) {
                        json = jArr.getJSONObject(i);
                        receiveMsg = json.getString("enumber") + "," + json.getString("password") + "," +
                                json.getString("ssn") + "," + json.getString("name") + "," +
                                json.getString("authority") + "," + json.getString("status")
                                + "," + json.getString("approval") + "," + json.getString("position");
                    }

                    System.out.println("receiveMsg: " + receiveMsg);
                }else if(select==4){
                    recMsg = new String[jArr.length()];
                    receiveMsg += Integer.toString(jArr.length())+"~";
                    count = jArr.length();
                        for(int i=0; i<jArr.length();i++) {
                            json = jArr.getJSONObject(i);
                            recMsg[i] = json.getString("Item_num") + "," + json.getString("Item_name") + "," +
                                    json.getString("SN") + "," + json.getString("Manufacture") + "," +
                                    json.getString("Model_name")+ "," +
                                    json.getString("Standard") + "," + json.getString("Dep_code")
                                    + "," + json.getString("Use_where") + "," + json.getString("Image")+","+
                                    json.getString("Get_date")+ ","+json.getString("Pro_date")+","
                                    +json.getString("Get_cost")+",";
                            receiveMsg += recMsg[i]+"!";
                        }
                    //receiveMsg += Integer.toString(count);
                }
            } else{
                System.out.println(conn.getErrorStream());
                Log.i("통신 결과", conn.getResponseCode()+"에러");
                // 통신이 실패했을 때 실패한 이유를 알기 위해 로그를 찍습니다.
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
}
