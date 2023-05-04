package com.woopaca.exercise10;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;

public class VoteViewModel extends ViewModel {

    private Context context;

    private final int[] voteCounts = new int[9];
    private final String[] masterpiecesName = new String[]{
            "독서하는 소녀", "꽃장식 모자 소녀", "부채가 많은 소녀", "이쁜 소녀", "꿀잠자는 소녀",
            "테라스의 두 자매", "피아노 훔치는 소녀", "훔친 피아노 앞의 소녀들", "해변에서..?"
    };

    public void setContext(Context context) {
        this.context = context;
    }

    public int getVoteCount(int index) {
        return voteCounts[index];
    }

    public void vote(int index) {
        if (voteCounts[index] == 5) {
            Toast.makeText(context,
                    "최대 투표 수를 초과하였습니다. ('" + masterpiecesName[index] + "')", Toast.LENGTH_SHORT).show();
            return;
        }
        voteCounts[index]++;
        Toast.makeText(context,
                masterpiecesName[index] + ": " + voteCounts[index] + "표", Toast.LENGTH_SHORT).show();
    }
}
