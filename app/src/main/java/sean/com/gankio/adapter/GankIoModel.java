package sean.com.gankio.adapter;

/**
 * Created by Sean on 17/3/25.
 */

public class GankIoModel {

    /**
     * _id : 58cca37b421aa95810795c86
     * createdAt : 2017-03-18T11:03:23.757Z
     * desc : Android TensorFlow MachineLearning Example (Building TensorFlow for Android)
     * images : ["http://img.gank.io/75f15c11-3fc0-4426-ac3b-d91352d3afd0","http://img.gank.io/76ef2be4-e495-4f72-9809-334d288ebfda"]
     * publishedAt : 2017-03-21T12:19:46.895Z
     * source : web
     * type : Android
     * url : https://github.com/MindorksOpenSource/AndroidTensorFlowMachineLearningExample
     * used : true
     * who : AMIT SHEKHAR
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private String images;//这里只取第一个图片的地址
    private int blankLines;//用来随机设置desc长度
    // 搜索福利多出的字段
    private String ganhuo_id;
    private String readability;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getBlankLines() {
        return blankLines;
    }

    public void setBlankLines(int blankLines) {
        this.blankLines = blankLines;
    }

    public String getGanhuo_id() {
        return ganhuo_id;
    }

    public void setGanhuo_id(String ganhuo_id) {
        this.ganhuo_id = ganhuo_id;
    }

    public String getReadability() {
        return readability;
    }

    public void setReadability(String readability) {
        this.readability = readability;
    }
}
