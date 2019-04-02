package analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.FileUtil;


/**
 * 寻找误诊原因
 *
 * @author 刘珍珍
 * @version 创建时间：2017年8月16日下午4:42:14
 */
public class ContFindReason {

    private String content;

    public String contFindReason(String content) {
        this.content = content;
        String reason = "";
        System.out.println("误诊原因：");
        FileUtil.writeLog("误诊原因：");
        reason += keyWordFindDisease2("讨论");
        reason += keyWordFindDisease2("易误诊为");
        reason += keyWordFindDisease2("误诊分析");
        reason += keyWordFindDisease2("没有重视");
        reason += keyWordFindDisease2("没有仔细");
        reason += keyWordFindDisease2("未检查");
        reason += keyWordFindDisease2("忽视");
        reason += keyWordFindDisease2("造成误诊的原因");
        reason += keyWordFindDisease2("病因诊断");
        reason += keyWordFindDisease2("特异性诊断");


        reason += keyWordFindDisease("误诊为", "主要原因");
        reason += keyWordFindDisease("因", "难以鉴别");
        reason += keyWordFindDisease("没有得到正确的诊断，与", "有关");
        reason += keyWordFindDisease("没有", "分析");
        reason += keyWordFindDisease("观察", "不细");


        return reason;
    }


    public String keyWordFindDisease2(String keyWord) {
        String reason = "";
        int indexStart = this.content.indexOf(keyWord);
        if (indexStart > 20) {
            int indexEnd = this.content.indexOf("。", indexStart);
            if (indexEnd > -1) {
                reason += content.substring(indexStart, indexEnd);
                System.out.println(reason);
                FileUtil.writeLog(reason);
            }
        }
        return reason;
    }

    public String keyWordFindDisease(String keyWord1, String keyWord2) {

        String reason = "";
        int indexStart = this.content.indexOf(keyWord1);
        if (indexStart > 20) {
            int indexEnd = this.content.indexOf("。", indexStart);
            if (indexEnd > -1) {
                reason += content.substring(indexStart, indexEnd);
                if (reason.contains(keyWord2)) {
                    System.out.println(reason);
                    FileUtil.writeLog(reason);
                }

            }
        }
        return reason;
    }

    public static void main(String[] args) {
        ContFindReason co = new ContFindReason();
        co.contFindReason("肺炎链球菌肺炎反复误诊一例陈晓香,张永娟(廊坊市人民医院,河北廊坊065000)关键词:肺炎,肺炎球菌性;误诊;胰腺炎中图分类号:R563.1　　　文献标识码:B文章编号:1002-3429(2006)11-0029-021　病例资料男,42岁。因饮酒后腹痛1天入院。患者于1天前饮酒后出现持续性上腹胀痛,以剑下及左上腹为重,不向其他部位放散,伴恶心、呕吐,呕吐为非喷射性胃内容物,无腹泻及发热,无咳嗽、咳痰及胸痛,在当地医院口服药物治疗无好转,遂入我院。查体:体温37.6℃,脉搏128/min,呼吸20/min,血压120/80mmHg。痛苦病容,巩膜无黄染,双肺呼吸音清,未闻及干湿性啰音,心率128/min,律齐,无杂音。腹平软,未见胃肠型及蠕动波,肝脾未触及,剑突下及左上腹有压痛,无肌紧张及反跳痛,下肢无水肿。考虑为急性胰腺炎。查血白细胞21.3×109/L,中性粒细胞0.88;血脂酶51U/L,淀粉酶79U/L;尿淀粉酶957U/L;立位X线腹平片及腹部、双肾B超均未见异常。给予禁食水、补液、抑酸等治疗后症状无好转,腹痛加剧,转普外科治疗。行胃肠减压后腹痛略缓解,但夜间突发呼吸困难,咳粉红色泡沫样痰,伴大汗、胸闷、气短,给予强心、利尿等药物治疗后略好转,诊断为急性左心衰,又转入心内科,予硝普钠、多巴胺等药物治疗后症状好转。丙氨酸转氨酶112U/L,天冬氨酸转氨酶197U/L,肌酸激酶4417U/L,肌酸激酶同工酶164U/L,乳酸脱氢酶810U/L,α-羟丁酸脱氢酶631U/L,红细胞沉降率13mm/h;动脉血气分析:pH7.37,氧分压48mmHg,二氧化碳分压37mmHg。心电图示多导联T波低平,ST段压低0.1～0.2mV;心脏B超未见异常;X线胸片示左肺感染。以“左肺肺炎”转入呼吸科,给予万古霉素、加替沙星治疗,1周后复查X线胸片示炎症明显吸收。痰培养报告肺炎链球菌生长,对青霉素、万古霉素、奈替米星及庆大霉素敏感,对三代头孢及大环内酯药物均耐药。确诊为肺炎链球菌肺炎,继续治疗2周·29·临床误诊误治2006年11月第19卷第11期　ClinicalMisdiagnosis＆Mistherapy,November2006,Vol.19,No.11后痊愈出院。2　讨论肺炎链球菌是革兰染色阳性球菌,是寄居在口腔及鼻咽部的一种正常菌群,当机体免疫力下降时侵入人体而致病。肺炎链球菌肺炎是常见病,约占社区获得性肺炎的半数,病理过程分为充血期、红色肝变期、灰色肝变期及消散期。典型临床表现为高热、寒战、咳嗽、血痰及胸痛,X线胸片示肺段或肺叶有急性炎性实变。近年来由于抗生素的广泛应用,发病症状、体征及X线改变均表现不典型[1]。本例早期表现为食欲缺乏、恶心、呕吐、腹痛、腹泻,因而误诊为急性胰腺炎;当出现多汗、发绀、心动过速、心律失常、呼吸困难、咳粉红色泡沫样痰时,又误诊为左心衰。本例多次误诊辗转3个科室才最后确诊,延误了治疗时间,给患者造成很大痛苦,应吸取教训。参考文献:[1]　谢灿茂.肺炎链球菌肺炎[A].见:叶任高,陆再英,主编.内科学[M].第6版.北京:人民卫生出版社,2004.19-21.(收稿时间:2006-07-15)儿童慢性咳嗽194例病因分析袁大华(广西医科大学第五附属医院,广西柳州545001)"
                + ".");
    }
}
