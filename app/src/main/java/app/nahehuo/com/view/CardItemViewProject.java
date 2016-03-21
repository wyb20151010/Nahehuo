package app.nahehuo.com.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.ProjectListDict;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * 卡片View项
 *
 * @author xmuSistone
 */
@SuppressLint("NewApi") public class CardItemViewProject extends FrameLayout {

    public ImageView iv_pro_pic, iv_pro_per_avater;
    private TextView tv_pro_title;
    private TextView tv_pro_title_detail;
    private TextView tv_project_com_name;
    private TextView tv_pro_com_position;
    private TagFlowLayout tfl_pro_tag;
    private Context mContext;

    public CardItemViewProject(Context context) {
        this(context, null);
    }


    public CardItemViewProject(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CardItemViewProject(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.item_card_project, this);
        mContext=context;
        iv_pro_pic = (ImageView) findViewById(R.id.iv_pro_pic);
        iv_pro_per_avater = (ImageView) findViewById(R.id.iv_pro_per_avater);
        tv_pro_title = (TextView) findViewById(R.id.tv_pro_title);
        tv_pro_title_detail = (TextView) findViewById(R.id.tv_pro_title_detail);
        tv_project_com_name = (TextView) findViewById(R.id.tv_project_com_name);
        tv_pro_com_position = (TextView) findViewById(R.id.tv_pro_com_position);
        tfl_pro_tag = (TagFlowLayout) findViewById(R.id.tfl_pro_tag);
    }


    public void fillData(final ProjectListDict itemData) {
        ImageLoader.getInstance().displayImage(itemData.getPersonPic(), iv_pro_per_avater);
        ImageLoader.getInstance().displayImage(itemData.getProjectPic(),
                iv_pro_pic);
        tv_pro_title.setText(itemData.getProjectTitle());
        tv_pro_title_detail.setText(itemData.getProjectTitleDetail());
        tv_project_com_name.setText(itemData.getProjectComName());
        tv_pro_com_position.setText(itemData.getProjectComPosition());

        tfl_pro_tag.setAdapter(new TagAdapter(itemData.getProjectTagList()) {

            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv= (TextView) LayoutInflater.from(mContext).inflate
                        (R.layout
                                .item_tag_bule,
                        parent,false);
                tv.setText(itemData.getProjectTagList().get(position));
                if(position==0){
                    tv.setBackgroundDrawable(getResources().getDrawable(R
                            .drawable.bg_shape_round_red));
                }else if(position==1){
                    tv.setBackgroundDrawable(getResources().getDrawable(R
                            .drawable.bg_shape_round_green));
                }
                return tv;
            }
        });
    }
}
