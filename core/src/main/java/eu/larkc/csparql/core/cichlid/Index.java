package eu.larkc.csparql.core.cichlid;


import eu.larkc.csparql.common.RDFTriple;

import java.util.*;

/**
 * @author 杨帆玉
 * @date 2019/12/3 4:06 下午
 */
public class Index {
    public static final int S__ = 1;
    public static final int SP_ = 2;
    public static final int S_O = 3;
    public static final int _P_ = 4;
    public static final int _PO = 5;
    public static final int __O = 6;

    int type;
    String s;
    String p;
    String o;
    int sIndex;
    int pIndex;
    int oIndex;

    public Index(int type, String s, String p, String o, int sIndex, int pIndex, int oIndex) {
        this.type = type;
        this.s = s;
        this.p = p;
        this.o = o;
        this.sIndex = sIndex;
        this.pIndex = pIndex;
        this.oIndex = oIndex;
    }

    public static Set<RDFTriple> getRelaRDF(Set<RDFTriple> rdfSet, ArrayList<Index> indexList) {
        long start = System.currentTimeMillis();
        boolean ok = false;
        Set<RDFTriple> out = new HashSet<>();
        for (Index curr: indexList) {
            for (RDFTriple rdf:rdfSet) {
                switch (curr.type) {
                    case 1:
                        if (rdf.subject.equals(curr.s))
                            out.add(rdf);
                        break;
                    case 2:
                        if (rdf.subject.equals(curr.s) && rdf.predicate.equals(curr.p))
                            out.add(rdf);
                        break;
                    case 3:
                        if (rdf.subject.equals(curr.s) && rdf.object.equals(curr.o))
                            out.add(rdf);
                        break;
                    case 4:
                        if (rdf.predicate.equals(curr.p))
                            out.add(rdf);
                        break;
                    case 5:
                        if (rdf.predicate.equals(curr.p) && rdf.object.equals(curr.o))
                            out.add(rdf);
                        break;
                    case 6:
                        if (rdf.object.equals(curr.o))
                            out.add(rdf);
                        break;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("常量索引时间: " + (end - start));
        return out;
    }

    public static Set<RDFTriple> getListSet(Set<RDFTriple> input, ArrayList<Index> indexList) {
        long start = System.currentTimeMillis();
//        List<RDFTriple> out = new ArrayList<>();
        Set<RDFTriple> out = new HashSet<>();
        // 定义存储 set 的 hashmap
        Map<Integer, List<Set<String>>> map = new HashMap<>();
        Map<Integer, Set<String>> mapSet = new HashMap<>();
        int valNum = 0;
        for (int i = 0; i < indexList.size(); i++) {
            valNum = Math.max(valNum, indexList.get(i).sIndex);
            valNum = Math.max(valNum, indexList.get(i).pIndex);
            valNum = Math.max(valNum, indexList.get(i).oIndex);
        }
        int[] valArr = new int[valNum];
        int[] curr = new int[valNum];
        for (int i = 0; i < valNum; i++) {
            valArr[i] = 0;
            curr[i] = 0;
        }
        for (Index in : indexList) {
            if (in.sIndex != 0) valArr[in.sIndex - 1]++;
            if (in.pIndex != 0) valArr[in.pIndex - 1]++;
            if (in.oIndex != 0) valArr[in.oIndex - 1]++;
        }
//        System.out.println(input.size());
//        初始化 map
        for (int i = 1; i <= valNum; i++) {
            List<Set<String>> list = new ArrayList<>(valArr[i-1]);
            for (int j = 0; j < valArr[i-1]; j++) {
                Set<String> set = new HashSet<>();
                list.add(set);
            }
            map.put(i, list);
            Set<String> set = new HashSet<>();
            mapSet.put(i, set);
        }
        // 存储 RDF 的 list
        List<List<RDFTriple>> rdfList = new ArrayList<>(indexList.size());
        for (int i = 0; i < indexList.size(); i++) {
            List<RDFTriple> table = new ArrayList<>();
            for (RDFTriple rdf: input) {
                if (check(rdf, indexList.get(i))) {
                    table.add(rdf);
                }
            }
            rdfList.add(table);
        }
        // 将索引放入 set 中
        for (int i = 0; i < rdfList.size(); i++) {
            Index index = indexList.get(i);
            for (int j = 0; j < rdfList.get(i).size(); j++) {
                RDFTriple rdf = rdfList.get(i).get(j);
                if (index.sIndex != 0) {
                    map.get(index.sIndex).get(curr[index.sIndex - 1]).add(rdf.subject);
                }
                if (index.pIndex != 0) {
                    map.get(index.pIndex).get(curr[index.pIndex - 1]).add(rdf.predicate);
                }
                if (index.oIndex != 0) {
                    map.get(index.oIndex).get(curr[index.oIndex - 1]).add(rdf.object);
                }
            }
            if (index.sIndex != 0) curr[index.sIndex - 1]++;
            if (index.pIndex != 0) curr[index.pIndex - 1]++;
            if (index.oIndex != 0) curr[index.oIndex - 1]++;
        }
        // 取出所有 set进行 join
//        System.out.println(map);
        for (int i = 1; i <= valNum; i++) {
            if (map.get(i).size() == 1) {
                mapSet.get(i).addAll(map.get(i).get(0));
            } else {
                map.get(i).sort(new Comparator<Set<String>>() {
                    @Override
                    public int compare(Set<String> set1, Set<String> set2) {
                        return set1.size() - set2.size();
                    }
                });
                //寻找相交元素
                for (String element: map.get(i).get(0)) {
                    boolean isOk = true;
                    int j = 1;
                    while (j < map.get(i).size()) {
                        if (!map.get(i).get(j).contains(element)) {
                            isOk = false;
                            break;
                        }
                        j++;
                    }
                    if (isOk) {
                        mapSet.get(i).add(element);
                    }
                }
            }
        }
        for (int i = 0; i < rdfList.size(); i++) {
            for (int j = 0; j < rdfList.get(i).size(); j++) {
                RDFTriple rdf = rdfList.get(i).get(j);
                Index index = indexList.get(i);
                switch (index.type) {
                    case 1:
                        if (mapSet.get(index.pIndex).contains(rdf.predicate) && mapSet.get(index.oIndex).contains(rdf.object))
                            out.add(rdf);
                        break;
                    case 2:
                        if (mapSet.get(index.oIndex).contains(rdf.object))
                            out.add(rdf);
                        break;
                    case 3:
                        if (mapSet.get(index.pIndex).contains(rdf.predicate))
                            out.add(rdf);
                        break;
                    case 4:
                        if (mapSet.get(index.sIndex).contains(rdf.subject) && mapSet.get(index.oIndex).contains(rdf.object))
                            out.add(rdf);
                        break;
                    case 5:
                        if (mapSet.get(index.sIndex).contains(rdf.subject))
                            out.add(rdf);
                        break;
                    case 6:
                        if (mapSet.get(index.sIndex).contains(rdf.subject) && mapSet.get(index.pIndex).contains(rdf.predicate))
                            out.add(rdf);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("变量索引时间: " + (end - start));
        return out;
    }

    private static boolean check(RDFTriple rdf, Index index) {
        boolean check = false;
        switch (index.type) {
            case 1:
                if (rdf.subject.equals(index.s))
                    return true;
                break;
            case 2:
                if (rdf.subject.equals(index.s) && rdf.predicate.equals(index.p))
                    return true;
                break;
            case 3:
                if (rdf.subject.equals(index.s) && rdf.object.equals(index.o))
                    return true;
                break;
            case 4:
                if (rdf.predicate.equals(index.p))
                    return true;
                break;
            case 5:
                if (rdf.predicate.equals(index.p) && rdf.object.equals(index.o))
                    return true;
                break;
            case 6:
                if (rdf.object.equals(index.o))
                    return true;
            default:
                return false;
        }
        return check;
    }

}
