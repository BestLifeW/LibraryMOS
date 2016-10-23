package com.rjxy.librarymos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lovec on 2016/9/22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Library_DB.db";
    private static final int DATABASE_VERSION = 1;
    public static final String USERINFO = "user_info";
    public static final String BOOKINFO ="book_info";
    public static final String ADMININFO="admin_info";
    public static final String BORROWINFO="borrow_info";
    private static final String TAG = "DatabaseHelper";
    public DatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "数据库初始化");
        init(db);
    }
    /*
    * 初始化数据
    * */
    private void init(SQLiteDatabase db) {
        db.execSQL("create table " + USERINFO + "( _id integer primary key AUTOINCREMENT,number varchar(30) ,password varchar(30),name varchar(30))");
        db.execSQL("create table " + ADMININFO + "( number varchar(30) primary key,password varchar(30))");
        db.execSQL("create table " + BOOKINFO + "( bookname varchar(30),number integer(10),isbn integer(10) primary key,author varchar(30),press varchar(30),pressyear varchar(30),category varchar(30),summary varchar(30),photo blob)");
        db.execSQL("create table " + BORROWINFO + "( _id integer primary key AUTOINCREMENT,number varchar(30) REFERENCES user_info(_id),isbn integer(10) REFERENCES book_info(isbn),borrowtime varchar(30)) ");
        initAdmin(db);
        initBook(db);
    }
    /*
    * 初始化管理员账户密码
    * */
    private void initAdmin(SQLiteDatabase db) {
        ContentValues value = new ContentValues();
        value.put("number","Admin");
        value.put("password","admin");
        db.insert(ADMININFO,null,value);
        Log.i(TAG, "初始化管理员数据成功");
    }

    /*
    * 初始化图书信息
    * */
    private void initBook(SQLiteDatabase db) {
        ContentValues value = new ContentValues();
        value.put("bookname","独立日");
        value.put("number","3");
        value.put("isbn","9787807681625");
        value.put("author","魏小河 / 木卫二 / 陈宇慧@田螺姑娘 ");
        value.put("press","生活•读书•新知三联书店 生活书店出版有限公司");
        value.put("pressyear","2016-9-1");
        value.put("category","散文");
        value.put("summary","本套装共三本：《独立日：用一间书房抵抗全世界》《独立日：用电影延长三倍生命》《独立日：日出之食》。");
        db.insert(BOOKINFO,null,value);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value1 = new ContentValues();
        value1.put("bookname","一句顶一万句");
        value1.put("number","3");
        value1.put("isbn","9787535486752");
        value1.put("author"," 刘震云");
        value1.put("press","长江文艺出版社");
        value1.put("pressyear"," 2016-8");
        value1.put("category","散文");
        value1.put("summary","本书是著名作家刘震云先生参磨四年的杠鼎之作。被北京五大评论家誉为“中国的《百年孤独》（诺贝尔获奖作家马尔克斯的作品）” ，是刘震云迄今为止最成熟最大气的作品。这部小说洗尽铅华，返璞归真，笔触始终紧贴苦难的大地和贱如草芥的底层人群，结构单纯而内容丰富，命悬一丝而荡气回肠，主人公常常走投无路而又一直勇往直前。这是当代文坛敢于直面真实的不可多得的佳作。著名评论家张颐武说，该书是刘震云“最具企图心和想象力的作品，也是他超越自我的最为坚韧的努力的成果。”");
        db.insert(BOOKINFO,null,value1);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value2 = new ContentValues();
        value2.put("bookname","魔鬼经济学1");
        value2.put("number","4");
        value2.put("isbn","9787508665931");
        value2.put("author"," [美]史蒂芬•列维特 / [美]史蒂芬•都伯纳");
        value2.put("press","中信出版集团");
        value2.put("pressyear","2016-9-1");
        value2.put("category","散文");
        value2.put("summary","从事更年期研究工作20年的诺斯鲁普博士通过亲身经历和众多精彩的案例，彻底颠覆了传统观点，证明绝经绝不仅仅意味着使女性失去生育能力。成为人见人厌的“黄脸婆”。相反。身体正在向你发出各种信号，提醒你生活将进入一个全新的层面。诺斯鲁普博士的更年期研究，给女性打开了一片全新的天地，她深厚的专业背景也为这部伟大作品提供了强大的说服力和实用价值，其中不乏前沿医学技术和最好的自然愈合方法。即使是专业的医务人员也能从中获益匪浅。这部充满爱、鼓舞和力量的著作将指导女性度过波动的更年期，从容、通达、高质量地享受余生。");
        db.insert(BOOKINFO,null,value2);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value3 = new ContentValues();
        value3.put("bookname","谁在收藏中国");
        value3.put("number","3");
        value3.put("isbn","9787508664309");
        value3.put("author"," [美] 谢林·布里萨克，卡尔·梅耶");
        value3.put("press","中信出版社");
        value3.put("pressyear","2016-8");
        value3.put("category","小说");
        value3.put("summary","在过去的两个世纪中，西方来到中国，从洞窟、宫殿和画商的密室里搜刮艺术珍品，盗走了雕塑、家具、瓷器、书画等大量国宝。敦煌壁画、龙门石窟、昭陵六骏……这些稀世珍宝现存何处？在盗宝者中，除了臭名昭著的斯坦因、华尔纳、卢芹斋，还有哪些鲜为人知的“幕后黑手”？两位作者通过查阅私人文件、历史档案，以及主要人物的回忆录，详细叙述了从鸦片战争到1949年这段时期，以美国人为首的西方收藏家是如何想方设法获得中国艺术品的一段历史，这些文物最终催生了中国古董市场在欧美的蓬勃发展，也激发了中国人依靠艺术市场促使国宝回归的努力。");
        db.insert(BOOKINFO,null,value3);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value4 = new ContentValues();
        value4.put("bookname","水彩风景绘");
        value4.put("number","2");
        value4.put("isbn","9787115427618");
        value4.put("author"," 米蒂");
        value4.put("press","人民邮电出版社");
        value4.put("pressyear","2016-9-1");
        value4.put("category","小说");
        value4.put("summary","米蒂，水彩插画师。现在中国传媒大学进修，Lofter优质插画师，堆糖达人，涂鸦王国优质作者。 2015年为毕淑敏老师的新书《欣喜是自酿的》画封面设计和部分插图。");
        db.insert(BOOKINFO,null,value4);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value5 = new ContentValues();
        value5.put("bookname","爱德华·巴纳德的堕落");
        value5.put("number","3");
        value5.put("isbn","9787549585472");
        value5.put("author","[英] 毛姆");
        value5.put("press","广西师范大学出版社");
        value5.put("pressyear","2016/10/1");
        value5.put("category","小说");
        value5.put("summary","马尔克斯将毛姆列为最钟爱的作者之一。奥威尔称毛姆是“影响我最大的现代作家，我深深地钦佩他摒除虚饰讲述故事的能力”。短篇小说在毛姆的创作中占有重要地位，安东尼·伯吉斯就曾评价他写下了“英语文学中最好的短篇故事”。《人性的枷锁》《刀锋》等长篇小说使毛姆名闻世界，而他的短篇则以编织故事的精湛技巧，对人性和社会生活的敏锐洞察，为其赢得了更多读者，成为一种全球现象。");
        db.insert(BOOKINFO,null,value5);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value6 = new ContentValues();
        value6.put("bookname","等一朵花开");
        value6.put("number","3");
        value6.put("isbn","9787550282483");
        value6.put("author"," 林帝浣");
        value6.put("press","北京联合出版公司·未读");
        value6.put("pressyear","2016-9-16");
        value6.put("category","散文");
        value6.put("summary","用很多的耐心和微笑，去等一朵花开放，有着非常重要的人生意义。\n" +
                "本书是著名作家林帝浣转型之作，由平日的一些水墨画、书法作品和一些零碎杂感文字结集而成，记录下琐碎生活中的无限乐趣:谈各地美食、品茶之趣，赏宋词，感受书法之美，发现生活中的小确幸，从中体悟人生的兴致与禅意。");
        db.insert(BOOKINFO,null,value6);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value7 = new ContentValues();
        value7.put("bookname","无罪辩护");
        value7.put("number","3");
        value7.put("isbn","9787505738027");
        value7.put("author"," 张海生");
        value7.put("press"," 中国友谊出版公司");
        value7.put("pressyear","2016-9-1");
        value7.put("category","小说");
        value7.put("summary","由简明、罗杰和张静组成的刑辩律师铁三角，致力于为冤者昭雪，让死者瞑目！\n" +
                "废弃的车辆，衣衫不整的女孩，指纹、齿痕、精液等种种痕迹统统指向一个人，是证据确凿还是精心设计的阴谋？\n" +
                "温文尔雅的大学教授手握钉头锤，是过失杀人还是被人陷害？\n" +
                "被拐卖的妇女反而成为贩卖妇女团伙的头目，是天性使然还是另有隐情？\n" +
                "还有公路游魂、陋室碎尸、校园凌霸等多个扑朔迷离的案件……\n" +
                "简明三人组该如何利用专业知识，查出谜案背后的一桩桩惊天秘密……");
        db.insert(BOOKINFO,null,value7);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value8 = new ContentValues();
        value8.put("bookname","一生欠安");
        value8.put("number","3");
        value8.put("isbn","9787537847759");
        value8.put("author"," 李梦霁 ");
        value8.put("press","北岳文艺出版社");
        value8.put("pressyear","2016-10-1");
        value8.put("category","散文");
        value8.put("summary","描绘教科书形象之外的名人面孔。\n" +
                "嫁与鲁迅、张学良、溥仪为妻，\n" +
                "同徐悲鸿、梅兰芳、宋子文谈恋爱，\n" +
                "成了杜月笙、郁达夫、戴笠的情人，\n" +
                "似是历史，皆是现实。\n" +
                "拂去显赫声名，他们终究不过一介寻常男子，\n" +
                "用来爱，用来恨，念念一生。\n" +
                "陈圆圆\n" +
                "柳如是\n" +
                "影后胡蝶\n" +
                "末代皇后婉容\n" +
                "鲁迅之妻朱安\n" +
                "顾城妻子谢烨\n" +
                "宋子文初恋盛七小姐\n" +
                "少帅张学良夫人于凤至\n" +
                "……\n" +
                "她们爱过的男人盛极一时、举世闻名，盛名之外的她们被辱骂、被误读、被遗忘、被抹杀，甚至背着红颜祸国的千古罪名……容貌倾城、才学出众、侠肝义胆，竟都未能成全她们的半世情缘，女子一生，何以为安？");
        db.insert(BOOKINFO,null,value8);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value9 = new ContentValues();
        value9.put("bookname","夜色人生");
        value9.put("number","7");
        value9.put("isbn","9787539992426");
        value9.put("author"," [美] 丹尼斯·勒翰");
        value9.put("press","江苏凤凰文艺出版社");
        value9.put("pressyear","2016-10-15");
        value9.put("category","小说");
        value9.put("summary","这是那种少有的既可以在读书俱乐部里引起激烈争论，又可以在飞机上消磨几小时时光的书。——读者评论\n" +
                "-\n" +
                "教父级别的传奇故事，伟大的角色，大量精彩的戏剧冲突。——读者评论\n" +
                "-\n" +
                "勒翰有着绝妙的语言表达方式。情节紧密交织，节奏干脆利落，角色人格丰富。从各种角度来看，《夜色人生》都是一部了不起的小说。——读者评论");
        db.insert(BOOKINFO,null,value9);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value10 = new ContentValues();
        value10.put("bookname","哥德尔、艾舍尔、巴赫");
        value10.put("number","3");
        value10.put("isbn","9787100013239");
        value10.put("author"," [美] 侯世达");
        value10.put("press","商务印书馆");
        value10.put("pressyear","1997-5");
        value10.put("category","科普");
        value10.put("summary","这是那种少有的既可以在读书俱乐部里引起激烈争论，又可以在飞机上消磨几小时时光的书。——读者评论\n" +
                "-\n" +
                "教父级别的传奇故事，伟大的角色，大量精彩的戏剧冲突。——读者评论\n" +
                "-\n" +
                "勒翰有着绝妙的语言表达方式。情节紧密交织，节奏干脆利落，角色人格丰富。从各种角度来看，《夜色人生》都是一部了不起的小说。——读者评论");
        db.insert(BOOKINFO,null,value10);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value11 = new ContentValues();
        value11.put("bookname","人类简史");
        value11.put("number","3");
        value11.put("isbn","9787508647357");
        value11.put("author"," [以色列]尤瓦尔·赫拉利");
        value11.put("press","中信出版社");
        value11.put("pressyear"," 2014-11-1");
        value11.put("category","科普");
        value11.put("summary","人类简史：从动物到上帝》是以色列新锐历史学家的一部重磅作品。从十万年前有生命迹象开始到21世纪资本、科技交织的人类发展史。十万年前，地球上至少有六个人种，为何今天却只剩下了我们自己？我们曾经只是非洲角落一个毫不起眼的族群，对地球上生态的影响力和萤火虫、猩猩或者水母相差无几。为何我们能登上生物链的顶端，最终成为地球的主宰？\n" +
                "从认知革命、农业革命到科学革命，我们真的了解自己吗？我们过得更加快乐吗？我们知道金钱和宗教从何而来，为何产生吗？人类创建的帝国为何一个个衰亡又兴起？为什么地球上几乎每一个社会都有男尊女卑的观念？为何一神教成为最为广泛接受的宗教？科学和资本主义如何成为现代社会最重要的信条？理清影响人类发展的重大脉络，挖掘人类文化、宗教、法律、国家、信贷等产生的根源。这是一部宏大的人类简史，更见微知著、以小写大，让人类重新审视自己。");
        db.insert(BOOKINFO,null,value11);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value12 = new ContentValues();
        value12.put("bookname","万物：创世");
        value12.put("number","3");
        value12.put("isbn","9787550243279");
        value12.put("author"," [德]延斯·哈德（Jens Harder）");
        value12.put("press"," 北京联合出版公司");
        value12.put("pressyear"," 2015-4");
        value12.put("category","科普");
        value12.put("summary","《万物：创世》诠释了作者对抽象和具象之间的张力的一种迷恋，全书视角在微观和宏观之间切换。哈德从碳原子讲到砖头，将DNA的双螺旋结构扭成电话线，同时又保持了本书作为一本严肃历史读物的角色。\n" +
                "——Die Zeit 德国《时代周报》\n" +
                "《万物：创世》与《万物：文明》上卷是漫画艺术这种表现形式中最野心、最一丝不苟的代表作。毫无争议。");
        db.insert(BOOKINFO,null,value12);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value13 = new ContentValues();
        value13.put("bookname","解忧杂货店");
        value13.put("number","3");
        value13.put("isbn","9787544270878");
        value13.put("author"," (日)东野圭吾");
        value13.put("press","南海出版公司");
        value13.put("pressyear"," 2014-5");
        value13.put("category","小说");
        value13.put("summary","现代人内心流失的东西，这家杂货店能帮你找回——\n" +
                "僻静的街道旁有一家杂货店，只要写下烦恼投进卷帘门的投信口，第二天就会在店后的牛奶箱里得到回答。\n" +
                "因男友身患绝症，年轻女孩静子在爱情与梦想间徘徊；克郎为了音乐梦想离家漂泊，却在现实中寸步难行；少年浩介面临家庭巨变，挣扎在亲情与未来的迷茫中……\n" +
                "他们将困惑写成信投进杂货店，随即奇妙的事情竟不断发生。\n" +
                "生命中的一次偶然交会，将如何演绎出截然不同的人生？\n" +
                "如今回顾写作过程，我发现自己始终在思考一个问题：站在人生的岔路口，人究竟应该怎么做？我希望读者能在掩卷时喃喃自语：我从未读过这样的小说。——东野圭吾");
        db.insert(BOOKINFO,null,value13);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value14 = new ContentValues();
        value14.put("bookname","百年孤独");
        value14.put("number","2");
        value14.put("isbn","9787544253994");
        value14.put("author"," [哥伦比亚] 加西亚·马尔克斯");
        value14.put("press","南海出版公司");
        value14.put("pressyear"," 2011-6");
        value14.put("category","小说");
        value14.put("summary","《百年孤独》是魔幻现实主义文学的代表作，描写了布恩迪亚家族七代人的传奇故事，以及加勒比海沿岸小镇马孔多的百年兴衰，反映了拉丁美洲一个世纪以来风云变幻的历史。作品融入神话传说、民间故事、宗教典故等神秘因素，巧妙地糅合了现实与虚幻，展现出一个瑰丽的想象世界，成为20世纪最重要的经典文学巨著之一。1982年加西亚•马尔克斯获得诺贝尔文学奖，奠定世界级文学大师的地位，很大程度上乃是凭借《百年孤独》的巨大影响。");
        db.insert(BOOKINFO,null,value14);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value15 = new ContentValues();
        value15.put("bookname","钓鱼的男孩");
        value15.put("number","3");
        value15.put("isbn","9787540476878");
        value15.put("author"," [尼日利亚] 奇戈希·奥比奥玛");
        value15.put("press","湖南文艺出版社");
        value15.put("pressyear","2016-9-1");
        value15.put("category","小说");
        value15.put("summary","这本小说只能用“令人敬畏”来形容。它散发出生命的活力，负载着死亡的重量，不论是文字风格还是故事的原始力量，都让人目眩神迷。很少有小说真正具备神话的力量，《钓鱼的男孩》绝对是这样一本书。一部壮丽的杰作。\n" +
                "——埃莉诺·卡顿（《发光体》作者、史上最年轻布克奖得主）\n" +
                "这个令人伤痛然而终获救赎的故事有一种清晰可见的优美，其直指人心的叙述力量简直令我无法呼吸……我读了一遍又一遍，尽管对其中犹如《圣经》故事一般的情节和人物已稔熟于胸，但每每行至令人惊叹的结尾处，我总是潸然泪下。——埃琳娜·拉宾（《钓鱼的男孩》英国版编辑）\n" +
                "《钓鱼的男孩》显然有其政治隐喻，却并不过度……对神秘与残杀、蚀人心骨的恐惧，以及非洲生命色调的探究质地饱满，硕果累累，尤为凸显的是，他在这个极富人性的非洲故事中展现出来的营造戏剧张力的才华……奇戈希·奥比奥玛无疑是钦努阿·阿契贝的接班人。");
        db.insert(BOOKINFO,null,value15);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value16 = new ContentValues();
        value16.put("bookname","中国历代政治得失");
        value16.put("number","3");
        value16.put("isbn","9787108015280");
        value16.put("author"," 钱穆");
        value16.put("press","生活•读书•新知三联书店 生活书店出版有限公司");
        value16.put("pressyear","2001");
        value16.put("category","历史");
        value16.put("summary","《中国历代政治得失》为作者的专题演讲合集，分别就中国汉、唐、宋、明、清五代的政府组织、百官职权、考试监察、财经赋税、兵役义务等种种政治制度作了提要勾玄的概观与比照，叙述因革演变，指陈利害得失。既高屋建瓴地总括了中国历史与政治的精要大义，又点明了近现代国人对传统文化和精神的种种误解。言简意赅，语重心长，实不失为一部简明的“中国政治制度史”。");
        db.insert(BOOKINFO,null,value16);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value17 = new ContentValues();
       value17.put("bookname","万历十五年");
       value17.put("number","3");
       value17.put("isbn","9787108009821");
       value17.put("author","[美] 黄仁宇 ");
       value17.put("press","生活•读书•新知三联书店 生活书店出版有限公司");
       value17.put("pressyear","97-5");
       value17.put("category","历史");
       value17.put("summary","万历十五年，亦即公元1587年，在西欧历史上为西班牙舰队全部出动征英的前一年；而在中国，这平平淡淡的一年中，发生了若干为历史学家所易于忽视的事件。这些事件，表面看来虽似末端小节，但实质上却是以前发生大事的症结，也是将在以后掀起波澜的机缘。在历史学家黄仁宇的眼中，其间的关系因果，恰为历史的重点，而我们的大历史之旅，也自此开始……\n" +
                "《万历十五年》是黄仁宇的成名之作，也是他的代表作之一。这本书融会了他数十年人生经历与治学体会，首次以“大历史观”分析明代社会之症结，观察现代中国之来路，给人启发良多。英文原本推出后，被美国多所大学采用为教科书，并两次获得美国书卷奖历史类好书的提名。");
        db.insert(BOOKINFO,null,value17);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value18 = new ContentValues();
        value18.put("bookname","夹边沟记事");
        value18.put("number","3");
        value18.put("isbn","9787536053281");
        value18.put("author"," 杨显惠 ");
        value18.put("press"," 花城出版社");
        value18.put("pressyear","2008-8");
        value18.put("category","历史");
        value18.put("summary","这是一段尘封四十年的历史，当年的幸存者散落在各个角落，没有人问过他们到底发生了什么，当年的死难者早已化为白骨，连他们的后代也不知道埋在何处。幸亏杨显惠这位有良知的作家，不辞辛劳，四处寻访，历经数载，终于揭开了历史的盖子。\n" +
                "本书是《定西孤儿院纪事》的姊妹篇，被誉为“中国的《古拉格群岛》”。书中所指的夹边沟，为甘肃酒泉一个羁押右派分子的劳改农场，从1957年开始关押的近三千人，至1960年底幸存者已不足一半，是一处充满了苦难、饥饿和死亡的伤痛之地。作者历经数年，大海捞针般搜寻和采访了近百名当事人，并在高度忠于历史事实的基础上，完成了这部极具震撼历的纪实性小说。全书通过近20个故事，对众多受难者命运的来龙去脉进行了深沉的揭示，对绝境中的人性有着十分出色的绘状，更直视了这一历史悲剧的精神本质和深刻教训。\n" +
                "从2000年春季《上海文学》开始连载《夹边沟记事》至今，时间已经过去了九年。其间天津古籍出版社出过一册《夹边沟记事》，但其内容不全是“夹边沟”，还有几篇作者早期的中短篇小说。后来上海文艺出版社出版了全部的“夹边沟”故事，书名变成了《告别夹边沟》。现在花城出版社要重新出版这本书，且恢复了它的原名。");
        db.insert(BOOKINFO,null,value18);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value19 = new ContentValues();
        value19.put("bookname","天龙八部");
        value19.put("number","5");
        value19.put("isbn","9787108006721");
        value19.put("author"," 金庸 ");
        value19.put("press","生活•读书•新知三联书店 生活书店出版有限公司");
        value19.put("pressyear","1994-5");
        value19.put("category","武侠");
        value19.put("summary","天龙八部乃金笔下的一部长篇小说，与《射雕》，《神雕》等 几部长篇小说一起被称为可读性最高的金庸小说。《天龙》的故事情节曲折，内容丰富，也曾多次被改编为电视作品。是金庸作品中集大成的一部。故事以南宋末年动荡的社会环境为背景，展开波澜壮阔的历史画卷，塑造了乔峰、段誉、虚竹、慕容复等形象鲜明的人物，成为武侠史上的经典之作。故事精彩纷呈，人物命运悲壮多变，是可读性很强的作品，具有震撼人心的力量");
        db.insert(BOOKINFO,null,value19);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value20 = new ContentValues();
        value20.put("bookname","刀背藏身");
        value20.put("number","10");
        value20.put("isbn","9787020098392");
        value20.put("author"," 徐皓峰 ");
        value20.put("press","人民文学出版社");
        value20.put("pressyear","2013-3");
        value20.put("category","武侠");
        value20.put("summary","《刀背藏身：徐皓峰武侠短篇集》收入徐皓峰2012年5月至2013年2月间完成的三个短篇新作：《师父》《国士》《刀背藏身》。");
        db.insert(BOOKINFO,null,value20);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value21 = new ContentValues();
        value21.put("bookname","七夜雪");
        value21.put("number","3");
        value21.put("isbn","9787530208687");
        value21.put("author"," 沧月");
        value21.put("press","北京十月文艺出版社");
        value21.put("pressyear"," 2006-10");
        value21.put("category","武侠");
        value21.put("summary","鼎剑阁霍展白为救治昔日恋人之子沫儿的病，用七年的时间拼死取得了药王谷主人薛紫夜开给他的七味绝世药引，魔教大光明宫排位第一的神秘杀手瞳为了自己能杀死魔教教主获得自由而抢夺龙血赤寒珠，霍展白和瞳在打斗中双双重伤，被薛紫夜送回药王谷治疗。沫儿的病事实上无法治疗，薛紫夜为一直隐 瞒着霍展白而不安，不顾自己寒症孱弱之身而设法寻找疗法。与此同时，薛紫夜震惊地发现这个能用眼神控制人精神的杀手瞳竟然是失散多年的儿时伙伴明介……");
        db.insert(BOOKINFO,null,value21);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value22 = new ContentValues();
        value22.put("bookname","走路健身法");
        value22.put("number","3");
        value22.put("isbn","9787550280526");
        value22.put("author"," [韩]金哲彦");
        value22.put("press","北京联合出版公司·后浪出版公司");
        value22.put("pressyear"," 2016-10-15");
        value22.put("category","健康");
        value22.put("summary","这是一本运动爱好者必备的入门书。\n" +
                "走路对于任何人来说都可以轻松开展，因此广受不擅长运动的人的青睐。作为有氧运动的代表，走路带给身体的负担较轻，实施起来较为方便，容易养成习惯，因此颇为流行。走路健身有很多益处，如预防疾病、减肥、消除疲劳、保持健康等。走路看似是世界上最简单的健身运动，然而走路爱好者们的姿势却千差万别。你真的会走路健身吗？如果方法不对，走路也会让你受伤。\n" +
                "作为专业的跑步教练，作者金哲彦总结了田径运动员的跑步秘诀，并灵活进行调整，教人用跑步的方法去走路，将走路变成了一门科学的运动入门项目。“躯干走路法”使你长时间走路也不会疲劳，膝盖和腰部不会疼痛，增加热量消耗，甚至能轻松进阶到跑步。这种走路方法还能矫正错误姿势，调理伤病，使走路的健身效果得到最大限度的提升。");
        db.insert(BOOKINFO,null,value22);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value23 = new ContentValues();
        value23.put("bookname","这书能让你戒烟");
        value23.put("number","3");
        value23.put("isbn","9787807027577");
        value23.put("author"," 亚伦•卡尔 ");
        value23.put("press"," 吉林文史出版社");
        value23.put("pressyear","2008");
        value23.put("category","健康");
        value23.put("summary","用一本书就可以戒烟？别开玩笑了！\n" +
                "如果你读过了，就不会这么说了。“这本书能让你戒烟”，这不仅仅是一个或几个烟民的体会，而是1000万成功告别烟瘾的人的共同心声。\n" +
                "如果你是一个老烟枪，你完全可以一边点上一支烟，一边细细阅读这本书。你会惊讶地发现，烟瘾会在阅读中不知不觉消失。\n" +
                "这本书的作者亚伦·卡尔，是一个有着33年烟龄、一天100支烟、声称“不抽烟毋宁死”的家伙。正是这位老烟枪发现了轻松戒烟的秘密！\n" +
                "亚伦·卡尔指出，只要停止吸烟1小时，人体血液中的尼古丁成分会下降到1/4；停止吸烟72小时，尼古丁在人体内的反应就会完全消失！许多人之所以无法摆脱烟瘾，并不是因为生理上的依赖，而是心理依赖，是因为我们内心的恐惧——\n" +
                "我们恐惧一旦离开香烟，就无法应对生活的压力；\n" +
                "我们恐惧一旦离开香烟，就会觉得非常无聊，注意力也会下降；\n" +
                "我们恐惧一旦离开香烟，就会脾气大发，情绪不佳；\n" +
                "不过，最让我们恐惧的还是戒烟无法成功，只能一辈子做烟瘾的奴隶！\n" +
                "殊不知：这些恐惧都是由吸烟导致的，永远无法靠吸烟缓解！\n" +
                "亚伦·卡尔的《这书能让你戒烟》，就是要帮你克服心理恐惧，然后轻轻松松告别烟瘾！开始吧！\n" +
                "如果你是一个吸烟者，只要读下去就可以了。\n" +
                "如果你自己不吸烟，买本书送给亲友，只要说服他们读下去就可以。\n" +
                "如果你自己无法说服他们，也可以阅读本书，最后一章会告诉你说服他们的方法。");
        db.insert(BOOKINFO,null,value23);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value24 = new ContentValues();
        value24.put("bookname","谷物大脑");
        value24.put("number","6");
        value24.put("isbn","9787111499411");
        value24.put("author"," （美）戴维•珀尔玛特（David Perlmutter） / 克里斯廷•洛伯格（Kristin Loberg）");
        value24.put("press","机械工业出版社");
        value24.put("pressyear","2015/5/20");
        value24.put("category","健康");
        value24.put("summary","美国健康类第一畅销书！\n" +
                "★ 出版80周长踞Amazon健康类排行榜第1名！\n" +
                "★ 甫出版，即登上《纽约时报》畅销书榜第1名！\n" +
                "★ 《纽约时报》畅销书榜连续在榜55周！\n" +
                "★ 《美国出版周报》畅销书榜连续在榜超40周！\n" +
                "好莱坞和运动界明星都在使用无麸质饮食法！\n" +
                "●　无麸质饮食法帮助小德获得43连胜、8次大满贯，并坐上了世界第一的宝座！\n" +
                "●　美国名模辛迪?克劳馥、好莱坞女星格温妮斯?帕特洛、麦莉?赛勒斯、美国前第一千金切尔西?克林顿、NBA明星球员史蒂夫?纳什、台湾著名作家胡因梦、日本著名演员中谷美纪等诸多名人都在使用无麸质饮食法！革命性的饮食法，颠覆了欧美人的饮食习惯！\n" +
                "▲　欧美掀无麸质饮食潮流，有高达1／3的美国人试图避免食用麸质\n" +
                "▲　美国无麸质食品市场规模增长至42亿美元，无麸质比萨、无麸质啤酒开始流行\n" +
                "你的大脑会生病，并非遗传基因早注定，\n" +
                "罪魁祸首，正是你每天所吃的食物！\n" +
                "麸质、碳水化合物和糖，\n" +
                "就像“沉默的杀手”，\n" +
                "在你毫无知觉的时候，\n" +
                "已对你的大脑与身体造成永久的损害。");
        db.insert(BOOKINFO,null,value24);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value25 = new ContentValues();
        value25.put("bookname","民国太太的厨房");
        value25.put("number","3");
        value25.put("isbn","9787508664163");
        value25.put("author"," 李舒 ");
        value25.put("press","中信出版集团 / 楚尘文化");
        value25.put("pressyear","2016-7");
        value25.put("category","美食");
        value25.put("summary","食物是探寻民国岁月的一把钥匙，有了它们，我们和那些闪光的名字之间，仿佛有了一座桥。\n" +
                "作者从“吃”下手，切入张爱玲、张大千、吴宓、黄侃、钱锺书、张恨水、周氏兄弟等二十余位民国时代文化大师的私生活，细数各位大咖的口味、嗜好、趣闻、雅事，并以此理出我们所熟悉的印象中难得的“陌生”。款款细述间，将这些大师再次予以生动描画、使之丰满。浓浓烟火气息中，这些文人的吃货本色一览无余——这，才是真实的他们。");
        db.insert(BOOKINFO,null,value25);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value26 = new ContentValues();
        value26.put("bookname","吃透法兰西");
        value26.put("number","3");
        value26.put("isbn","9787532749799");
        value26.put("author"," [英]彼得·梅尔 ");
        value26.put("press","上海译文出版社");
        value26.put("pressyear","2010-10");
        value26.put("category","美食");
        value26.put("summary","十九岁的彼得·梅尔初次尝到抹着黄油的法国面包，他沉睡多年的味蕾彻底苏醒了！他开始了他的老饕生涯，带着刀叉与瓶塞钻，走遍法国各地。他尝过最丰润柔软的金黄色煎蛋饼，上面撒了好多片“黑钻石”松露，口感既松软又结实，刀子滑过蛋皮，浓稠亮丽的蛋黄立刻涌出如蜜；他嚼过肉质比顶级牛排还嫩的蜗牛肉，连蜗牛壳里伴着蒜香的黄油汁都要一口吸尽，还要用一片面包把流蹿到下巴上的汁液抹了又抹才善罢甘休…… 在这本半是旅游见闻、半是美食指南的作品中，彼得·梅尔投入法国各地美食庆典的怀抱，体验法国人对吃的执着和艺术。透过作者风趣、精彩、趣味十足的叙述，让读者充分感受到法国人对于吃有多么认真！");
        db.insert(BOOKINFO,null,value26);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value27 = new ContentValues();
        value27.put("bookname","明日的便当");
        value27.put("number","3");
        value27.put("isbn","9787550282414");
        value27.put("author"," [日]饭岛奈美 ");
        value27.put("press","北京联合出版公司·后浪出版公司");
        value27.put("pressyear","2016-9-1");
        value27.put("category","美食");
        value27.put("summary","伴着一部部美食日剧在国内的人气高涨，其背后的料理设计师饭岛奈美也被越来越多的人熟知。饭岛奈美总是能用简单质朴的食物传递出浓浓的温情，这本书也是如此，简单快手的便当，是家人间深深的羁绊。母亲忧心家人 营养，儿女挂心有点“三高”倾向的父亲… 一份简单的便当，包含了很多深层的意义。如同饭岛老师在书中所说：“吃的人可能当时不会想太多，但是那时的味道，总有一天一定会成为令人念念不忘的绝佳回忆。”");
        db.insert(BOOKINFO,null,value27);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value28 = new ContentValues();
        value28.put("bookname","学会花钱");
        value28.put("number","3");
        value28.put("isbn","9787210083047");
        value28.put("author"," [日]野口真人 ");
        value28.put("press","江西人民出版社·后浪出版公司");
        value28.put("pressyear","2016-9");
        value28.put("category","理财");
        value28.put("summary","金牌得主的奖金是消费？办健身卡是投资？买著名画家的画作是投机？你每天花出去的金钱，到底是消费、投资还是投机？房屋的真实价值是房租的200倍？钻石昂贵真的是物以稀为贵吗？彩票是不理性的投机，为什么还有那么多人买？高档商区的咖啡价高，是因为就算昂贵也能卖出去？\n" +
                "现代金融理论到底是颠覆了我们习以为常的事，还是为我们提供了全新的视角，在本书中，作者将用故事教会你日常必备的金融学、行为经济学和统计学的知识。不管是工作、创业还是日常生活，从此不用怕没准备。");
        db.insert(BOOKINFO,null,value28);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value29 = new ContentValues();
        value29.put("bookname","富爸爸，穷爸爸");
        value29.put("number","3");
        value29.put("isbn","9787506246743");
        value29.put("author"," （美）罗伯特・T・清崎 / 莎伦・L・莱希特");
        value29.put("press","世界图书出版公司");
        value29.put("pressyear","2000-9");
        value29.put("category","理财");
        value29.put("summary","《富爸爸，穷爸爸》是一个真实的故事，作者罗伯特・清崎的亲生父亲和朋友的父亲对金钱的看法截然不同，这使他对认识金钱产生了兴趣，最终他接受了朋友的父亲的建议，也就是书中所说的。“富爸爸”的观念，即不要做金钱的奴隶，要让金钱为我们工作，并由此成为一名极富传奇色彩的成功的投资家。");
        db.insert(BOOKINFO,null,value29);
        Log.i(TAG, "初始化图书数据成功");

        ContentValues value30 = new ContentValues();
        value30.put("bookname","聪明的投资者");
        value30.put("number","3");
        value30.put("isbn","9787115234957");
        value30.put("author"," 本杰明·格雷厄姆 / Benjamin Graham ");
        value30.put("press","人民邮电出版社");
        value30.put("pressyear","2010-9-1");
        value30.put("category","理财");
        value30.put("summary","“有史以来，关于投资的最佳著作。”——沃伦•巴菲特\n" +
                "“完整地传达了格雷厄姆的巨大成功和广受欢迎的投资方法所包含的基本原则。”——《货币》杂志\n" +
                "\uF076\n" +
                "“格雷厄姆对于投资的意义就像欧几里得对于几何学、达尔文对于生物进化论一样重要。”——纽约证券分析协会\n" +
                "“格雷厄姆的思想，从现在起直到100年后，将会永远成为理性投资的基石。”——沃伦•巴菲特\n" +
                "假如你一生只读一本关于投资的论著，无疑就是这本《聪明的投资者》。 ——著名财经杂志《财富》的评论");
        db.insert(BOOKINFO,null,value30);
        Log.i(TAG, "初始化图书数据成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
