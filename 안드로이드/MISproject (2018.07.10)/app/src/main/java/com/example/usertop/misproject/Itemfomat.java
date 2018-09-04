package com.example.usertop.misproject;

import android.widget.ImageView;

public class Itemfomat {
    public Itemfomat(ImageView _사진, String _모델명 , String _제조업체명 , String _품명 , String _규격 , String _수량 , String _취득단가){
        사진 = _사진;
        모델명 = _모델명;
        제조업체명 =_제조업체명;
        품명 = _품명;
        규격 = _규격;
        수량 = _수량;
        취득단가 = _취득단가;
    }//constructor
    ImageView 사진;
    String 모델명;
    String 제조업체명;
    String 품명;
    String 규격;
    String 수량;
    String 취득단가;
}
