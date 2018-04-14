package com.android.quangthai.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity {
    //Khai báo các thành phần của app
    private TextView txtMath;
    private TextView txtResult;
    private int[] idButton = {
            R.id.btnOneZero,
            R.id.btnTwoZeroes,
            R.id.btnOne,
            R.id.btnTwo,
            R.id.btnThree,
            R.id.btnFour,
            R.id.btnFive,
            R.id.btnSix,
            R.id.btnSeven,
            R.id.btnEight,
            R.id.btnNine,
            R.id.btnDot,
            R.id.btnPlus,
            R.id.btnMinus,
            R.id.btnMultiply,
            R.id.btnDivide,
            R.id.btnOpen,
            R.id.btnClose,
            R.id.btnC,
            R.id.btnEqual,
            R.id.btnDelete,
            R.id.btnPercent,
            R.id.btnOneDivideX,
            R.id.btnXPowerY,
            R.id.btnPlusAnMinus
    };
    @Override
    //Hàm khởi tạo ứng dụng
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatorlayout);
        addControls();
    }
    //Reset giao diện tính toán
    private void init()
    {
        txtMath.setText("|");
        txtResult.setText("");
    }
    //Khởi tạo các biến
    private void addControls() {
        txtMath = (TextView)findViewById(R.id.txtMath);
        txtResult = (TextView)findViewById(R.id.txtResult);
        init();
        for (int i = 0; i < idButton.length; i ++)
        {
            //Tạo listener cho các button
            findViewById(idButton[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //id của nút được bấm
                    int id = view.getId();
                    //chuỗi tính toán
                    String math;
                    //nếu là nút clear
                    if (id == R.id.btnC)
                    {
                        init();
                    }
                    //nút xuất kết quả
                    else if (id == R.id.btnEqual)
                    {
                        math = txtMath.getText().toString();
                        if(math.contains("%"))
                        {
                            math = math.replaceAll("%", "/100");
                        }
                        calculate(math);
                    }
                    //nút xóa 1 kí tự
                    else if (id == R.id.btnDelete)
                    {
                        int length = txtMath.getText().length();
                        if(length > 0)
                        {
                            txtMath.setText(txtMath.getText().toString().substring(0, length - 1));
                        }
                    }
                    //nút tính nghịch đảo
                    else if (id == R.id.btnOneDivideX)
                    {
                        math = txtMath.getText().toString();
                        calculate("1.0/(" + math + ")");
                    }
                    //nút đổi dấu
                    else if (id == R.id.btnPlusAnMinus)
                    {
                        math = txtMath.getText().toString();
                        if (math.charAt(0) == '-')
                        {
                            txtMath.setText(math.substring(1));
                        }
                        else
                        {
                            txtMath.setText('-' + math);
                        }
                    }
                    //các nút còn lại
                    else {
                        for (int i = 0; i < idButton.length; i++) {
                            if (id == idButton[i]) {
                                String text = ((Button) findViewById(id)).getText().toString();
                                if (txtMath.getText().toString().trim().equals("|")) {
                                    txtMath.setText("");
                                }
                                txtMath.append(text);
                                return;
                            }
                        }
                    }
                }
            });
        }
    }
    //hàm tính toán dùng balan đảo của thư viện ngoài
    private void calculate(String math) {
        if(math.length() > 0)
        {
            Balan balan = new Balan();
            String result = balan.valueMath(math) + "";
            String error = balan.getError();
            if(error != null)
            {
                txtResult.setText(error);
            }
            else
            {
                txtResult.setText(result);
            }
        }
    }
}
