package daliuren;

/**
 * 此类仅用于研究六壬相关技术
 * 不懂人士兡忽略
 * 亥为登明一月将，戌为河魁二月将，
 * 酉为从魁三月将，申为传送四月将。
 * 未为小吉五月将，午为胜光六月将。
 * 巳为太乙七月将，辰为天罡八月将。
 * 卯为太冲九月将，寅为功曹十月将。
 * 丑为大吉十一月将，子为神后十二月将。
 * 登明（亥）：主田宅、文书、争讼、徵召事。
 * 河魁（戌）：主坟墓、骸骨、僧人、孤寡等。
 * 天罡（辰）：主医药、官司、斗讼之事。
 */
public class LiuRen {

}

abstract class ZHI {
    String mingzi;
    String tiangan;
    String yuejiang;

    ZHI() {
        this.mingzi = null;
        this.tiangan = null;
        this.yuejiang = null;
    }

    ZHI(String mingzi, String tiangan, String yuejiang) {
        this.mingzi = mingzi;
        this.tiangan = tiangan;
        this.yuejiang = yuejiang;
    }

    public void setMingzi(String mingzi) {
        this.mingzi = mingzi;
    }

    public String getMingzi() {
        return this.mingzi;
    }

    public void settiangan(String tiangan) {
        this.tiangan = tiangan;
    }

    public String gettiangan() {
        return this.tiangan;
    }

    public void setyuejiang(String yuejiang) {
        this.yuejiang = yuejiang;
    }

    public String getyuejiang() {
        return this.yuejiang;
    }
}

abstract class PAN {
    PAN before;
    PAN after;
    ZHI dizhi;

    PAN() {
        this.before = null;
        this.after = null;
        this.dizhi = null;
    }

    PAN(PAN before, PAN after, ZHI dizhi) {
        this.before = before;
        this.after = after;
        this.dizhi = dizhi;
    }
}

class DZI extends ZHI {
    DZI() {
        super("子", "空", "神后");
    }
}

class DCHOU extends ZHI {
    DCHOU() {
        super("丑", "癸", "大吉");
    }
}

class DYIN extends ZHI {
    DYIN() {
        super("寅", "甲", "功曹");
    }
}

class DMAO extends ZHI {
    DMAO() {
        super("卯", "空", "太冲");
    }
}

class DCHEN extends ZHI {
    DCHEN() {
        super("辰", "乙", "天罡");
    }
}
class DSI extends ZHI {
    DSI() {
        super("巳", "丙戊", "太乙");
    }
}
class DWU extends ZHI {
    DWU() {
        super("午", "空", "胜光");
    }
}
class DWEI extends ZHI {
    DWEI() {
        super("未", "丁己", "小吉");
    }
}
class DSHEN extends ZHI {
    DSHEN() {
        super("申", "庚", "传送");
    }
}
class DYOU extends ZHI {
    DYOU() {
        super("酉", "空", "丛魁");
    }
}
class DXU extends ZHI {
    DXU() {
        super("戌", "辛", "河魁");
    }
}
class DHAI extends ZHI {
    DHAI() {
        super("亥", "壬", "登明");
    }
}