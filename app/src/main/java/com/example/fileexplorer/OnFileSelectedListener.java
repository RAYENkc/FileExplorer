package com.example.fileexplorer;

import android.util.Log;

import java.io.File;

public interface OnFileSelectedListener {
    void onFileClicked(File file);
    void  onFileLongClicked(File file, int position);


}
