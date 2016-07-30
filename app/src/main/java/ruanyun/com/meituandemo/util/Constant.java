package ruanyun.com.meituandemo.util;

/**
 * @author Daniel
 * @ClassName: ${file_name}
 * @Description:
 * @date ${date}${time}
 */
public class Constant {
    // 编辑个人资料的类型
    public static final int TYPE_EDIT_TEXT = 1;
    public static final int TYPE_WHEEL_GENDER = 2;
    public static final int TYPE_WHEEL_USER_TYPE = 3;
    public static final int TYPE_WHEEL_SOURCE_SCHOOL = 4;
    public static final int TYPE_WHEEL_SOURCE_MAJOR = 5;
    public static final int TYPE_WHEEL_FIRST_SCHOOL = 6;
    public static final int TYPE_WHEEL_FIRST_MAJOR = 7;
    public static final int TYPE_WHEEL_SECOND_SCHOOL = 8;
    public static final int TYPE_WHEEL_SECOND_MAJOR = 9;
    public static final int TYPE_WHEEL_ACCEPT_SCHOOL = 10;
    public static final int TYPE_WHEEL_ACCEPT_MAJOR = 11;
    public static final int TYPE_EDIT_TEXT_ACTIVITY = 12;
    public static final int TYPE_TIME_PICKER = 13;

    // intent的action
    public static final String ACTION_REFRESH_PERSON_INFO = "com.zbzhixue.app.action_refresh_person_info";
    public static final String ACTION_REFRESH_COURSE_FOCUS = "com.zbzhixue.app.action_refresh_course_focus";
    public static final String ACTION_REFRESH_COURSE_CHOOSE = "com.zbzhixue.app.action_refresh_course_choose";
    public static final String ACTION_REFRESH_ONLINE_COURSE = "com.zbzhixue.app.action_refresh_online_course";

    // preference存储字段
    public static final String PREF_COURSE_POSITION = "pref_course_position";
    public static final String PREF_SETTINGS_TIME_STATUS = "pref_settings_time_status";
    public static final String PREF_LOGIN_NAME = "pref_login_name";
    public static final String PREF_HAS_LOGIN = "pref_has_login"; // 自动登录标签
    public static final String PREF_USER_INFO_USER_STRING = "pref_user_info_user_string"; // 用户信息字段
    public static final String PREF_USER_INFO_PROFILE_STRING = "pref_user_info_profile_string"; // 用户信息字段
    public static final String PREF_COURSES_STRING = "pref_user_info_string"; // 用户关注课程字段

    // 训练类型
    public static final String TRAIN_TYPE = "train_type";
    public static final int TYPE_BASE_TRAIN = 1;
    public static final int TYPE_STRONG_TRAIN = 2;
    public static final int TYPE_HARD_TRAIN = 3;
    public static final int TYPE_SIMULATE_TRAIN = 4;
    public static final int TYPE_REAL_TRAIN = 5;
}
