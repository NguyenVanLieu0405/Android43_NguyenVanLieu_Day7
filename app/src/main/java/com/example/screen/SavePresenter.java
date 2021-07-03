package com.example.screen;

public class SavePresenter {
    ISave iSave;

    public SavePresenter(ISave iSave) {
        this.iSave = iSave;
    }
    public  void onSave(String title,String des,String time,String date,String type,String tags,String weeks){
        if(title.equals("")||time.equals("")||date.equals("")||type.equals("")||tags.equals("")||weeks.equals("")||des.equals("")) iSave.onSaveError("Lưu thất bại! Vui lòng điền đầy đủ thông tin");
        else iSave.onSaveSuccessful("Lưu thành công");
    }
}
