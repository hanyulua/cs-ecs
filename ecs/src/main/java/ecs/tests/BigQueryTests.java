package ecs.tests;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.QueryFactory;
import ecs.models.*;
import eu.larkc.csparql.common.RDFTriple;
import org.mapdb.DB;
import org.mapdb.Serializer;

import java.util.*;

/**
 * @author 杨帆玉
 * @date 2020/1/6 9:45 下午
 */
public class BigQueryTests {

    public static Set<RDFTriple> input = new HashSet<>();

    public static HashMap<BigQueryPattern, HashSet<ArrayList<BigECSTuple>>> queryAnswerListSet2 ;

    public static HashSet<BigExtendedCharacteristicSet> visited = new HashSet<BigExtendedCharacteristicSet>();

    static public HashMap<Node, Integer> varMap = new HashMap<Node, Integer>();

    static public HashMap<Integer, Node> reverseVarMap = new HashMap<Integer, Node>();

    public static Map<String, Integer> propertiesSet ;

    public static Map<Integer, String> reversePropertiesSet ;

    public static int nextVar = -1;

    public static Map<Integer, HashMap<Integer, Integer>> propIndexMap ;

    public static Map<BigExtendedCharacteristicSet, Integer> ecsIntegerMap ;

    public static HashMap<Node, Integer> varIndexMap;

    public static Map<Integer, long[]> ecsLongArrayMap ;

    public static Map<Integer, long[]> csMap ;

    public static Map<Integer, BigCharacteristicSet> rucs ;

    public static Map<BigCharacteristicSet, Integer> ucs ;

    public static Map<String, Integer> intMap ;

    public static Map<Integer, String> reverseIntMap ;

    public static void main(String[] args) throws InterruptedException {
//        long start;
//        long end;
//        for (int i = 0; i < 100; i++) {
//            initSet();
//        }
//        for (;;) {
//            start = System.currentTimeMillis();
//            query(0, input);
//            end = System.currentTimeMillis();
//            System.out.println("queryTime: " + (end - start));
//            System.out.println();
//            Thread.sleep(1000);
//        }
        Set<RDFTriple> input = new HashSet<>();
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Bob", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Bobplain"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Bob", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#origin", "Ireland"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Bob", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#birthday", "1986"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Bob", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#RadioCom"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#John", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "JohnDoe"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#John", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#origin", "USA"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#John", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#birthday", "1976"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#John", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#RadioCom"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Jack", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Jack Doe"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Jack", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#origin", "UK"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Jack", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#birthday", "1980"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Jack", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#marriedTo", "Alice"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Jack", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#RadioCom"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#RadioCom", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#label", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Radio.Com"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#RadioCom", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#address", "21 Jump St."));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#RadioCom", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#managedBy", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Mike"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#RadioCom", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#registeredIn", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UKRegistry"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Mike", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#position", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Director"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UKRegistry", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#label", "UKCompanyRegistry"));
        input.add(new RDFTriple("http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UKRegistry", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Registrar"));
        query(2, input);
    }

    public static long query(int queryId, Set<RDFTriple> input) {
//        System.out.println(input.toString());
        long start1;
        long end1;
        start1 = System.currentTimeMillis();
        DataMap dataMap = new DataMap();
        end1 = System.currentTimeMillis();
        System.out.println("创建DB: " + (end1-start1));
        dataMap.init(input);
        end1 = System.currentTimeMillis();
        System.out.println("init time: " + (end1 - start1));

//        System.out.println("Loading database file.");
        DB db = dataMap.getDb();
//        System.out.println("Loading ecs map.");
        ecsLongArrayMap = db.hashMapCreate("ecsLongArrays")
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.LONG_ARRAY)
                .makeOrGet();


        Map<Integer, BigExtendedCharacteristicSet> ruecs = db.hashMapCreate("ruecsMap")
                .keySerializer(Serializer.INTEGER)
                //.valueSerializer(Serializer.)
                .makeOrGet();
//        System.out.println("Loading ruecs map.");

//        intMap = db.treeMapCreate("intMap")
//                .keySerializer(Serializer.STRING)
//                .valueSerializer(Serializer.INTEGER)
//                .makeOrGet();
        intMap = dataMap.intMap;
//		System.out.println("intMap: " + intMap.toString());
//        System.out.println("Loading int map.");

        reverseIntMap = db.hashMapCreate("reverseIntMap")
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.STRING)
                .makeOrGet();
//        System.out.println("Loading reverse int map.");

        csMap = db.hashMapCreate("csMap")
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.LONG_ARRAY)
                .makeOrGet();
//        System.out.println("Loading cs map.");
		/*int totalInCS = 0;
		long one = 0 ;
		for(Integer c : csMap.keySet()){
			totalInCS += csMap.get(c).length;
			one = csMap.get(c)[csMap.get(c).length/5];
			break;
		}
		for(Integer e : ecsLongArrayMap.keySet()){
			long[] dff = ecsLongArrayMap.get(e);
			if(indexOfTriple(dff, one)>=0)
				System.out.println("found");
		}*/
        rucs = db.hashMapCreate("rucsMap")
                .keySerializer(Serializer.INTEGER)
                //.valueSerializer(Serializer.)
                .makeOrGet();
//        System.out.println("Loading rucs map.");

        ucs = new HashMap<BigCharacteristicSet, Integer>();
        for(Integer ci : rucs.keySet()){
            ucs.put(rucs.get(ci), ci);
        }

        ecsIntegerMap = db.hashMapCreate("uecsMap")
                .valueSerializer(Serializer.INTEGER)
                //.valueSerializer(Serializer.)
                .makeOrGet();
//        System.out.println("Loading uecs map.");

        Map<BigExtendedCharacteristicSet, HashSet<BigExtendedCharacteristicSet>> ecsLinks = db.hashMapCreate("ecsLinks")
                //.keySerializer(Serializer.INTEGER)
                .makeOrGet();
//        System.out.println("Loading ecs links map.");

        int tot = 0;
        for(Integer ecs : ruecs.keySet()){
            tot += ecsLongArrayMap.get(ecs).length;
        }

//        System.out.println("total mapped triples: " + tot);
        tot = 0;
        for(BigExtendedCharacteristicSet e : ecsLinks.keySet()){
            tot += ecsLinks.get(e).size();
        }
//        System.out.println("total ecs links: " + tot);
//        System.out.println("ECS Links size: " + ecsLinks.size());

        Map<Integer, int[]> properIndexMap = db.hashMapCreate("propIndexMap")
                .keySerializer(Serializer.INTEGER)
                .valueSerializer(Serializer.INT_ARRAY)
                .makeOrGet();
//        System.out.println("Loading property index map.");

        propIndexMap = new HashMap<Integer, HashMap<Integer,Integer>>();
        for(Integer e : properIndexMap.keySet()){
            HashMap<Integer, Integer> d = propIndexMap.getOrDefault(e, new HashMap<Integer, Integer>());
            for(int i = 0; i < properIndexMap.get(e).length; i++){
                if(properIndexMap.get(e)[i] >= 0){
                    d.put(i, properIndexMap.get(e)[i]);
                }
            }
            propIndexMap.put(e, d);
        }

        propertiesSet = db.hashMapCreate("propertiesSet")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.INTEGER)
                .makeOrGet();
//        System.out.println("Loading properties set.");

        reversePropertiesSet = new HashMap<Integer, String>();

//        System.out.println(propertiesSet.toString());


        HashMap<BigExtendedCharacteristicSet, HashSet<Vector<BigExtendedCharacteristicSet>>> ecsVectorMap = new HashMap<BigExtendedCharacteristicSet, HashSet<Vector<BigExtendedCharacteristicSet>>>();

        HashSet<Vector<BigExtendedCharacteristicSet>> ecsVectors = new HashSet<Vector<BigExtendedCharacteristicSet>>();

        for(BigExtendedCharacteristicSet ecs : ecsLinks.keySet()){

// 			if(true) break;

            HashSet<BigExtendedCharacteristicSet> visited = new HashSet<BigExtendedCharacteristicSet>();

            Stack<Vector<BigExtendedCharacteristicSet>> stack = new Stack<>();

            Vector<BigExtendedCharacteristicSet> v = new Vector<BigExtendedCharacteristicSet>();

            v.add(ecs);

            stack.push(v);

            while(!stack.empty()){

                v = stack.pop();

                BigExtendedCharacteristicSet current = v.lastElement();

                visited.add(current);

                if(!ecsLinks.containsKey(current)){

                    if(ecsVectorMap.containsKey(current))
                        ecsVectorMap.get(current).add(v);
                    else{
                        HashSet<Vector<BigExtendedCharacteristicSet>> d = new HashSet<Vector<BigExtendedCharacteristicSet>>();
                        d.add(v);
                        ecsVectorMap.put(current, d);
                    }
                    ecsVectors.add(v);
                    continue;

                }

                for(BigExtendedCharacteristicSet child : ecsLinks.get(current)){
                    if(!visited.contains(child)){
                        Vector<BigExtendedCharacteristicSet> _v = new Vector<BigExtendedCharacteristicSet>();
                        _v.addAll(v);
                        _v.add(child);
                        stack.push(_v);
                    }
                }


            }
        }

        System.out.println("total patterns: " + ecsVectors.size());

        String queryString;
        Queries lubm = new Queries();
        ArrayList<Long> times;


        //args[1] contains the query set to test (0=LUBM, 1=Reactome, 2=Geonames)



//        for(String qs : lubm.getQueries(queryId)){


            try{
                queryString = lubm.getQuery(queryId);

                times = new ArrayList<Long>();

                //Prints the next query string

//                System.out.println(queryString);
                com.hp.hpl.jena.query.Query q= QueryFactory.create(queryString);
                //List<Var> projectVariables = q.getProjectVars();
                long tstart = System.currentTimeMillis();
                //int totalresults = 0;
                long tend ;

                BigECSQuery ecsq = new BigECSQuery(q);

                ecsq.findJoins();
//		 		ECSTree queryTree = ecsq.getEcsTree();


                HashSet<LinkedHashSet<BigExtendedCharacteristicSet>> queryListSet = BigECSQuery.getListSet();

                queryAnswerListSet2 = new HashMap<BigQueryPattern, HashSet<ArrayList<BigECSTuple>>>();

                for(LinkedHashSet<BigExtendedCharacteristicSet> thisQueryList : queryListSet){

                    ArrayList<BigExtendedCharacteristicSet> qlist = new ArrayList<>(thisQueryList);

                    ArrayList<BigECSTuple> qlistTuple = new ArrayList<BigECSTuple>();
                    for(BigExtendedCharacteristicSet qe : qlist){
                        //String pr = qe.predicate.getURI();
		 					/*if(pr.contains("swat"))
		 						pr = pr.replaceAll("#", "##");*/
                        BigECSTuple nt ;
                        if(qe.predicate.isURI()) {
                            System.out.println(qe.predicate.toString());
                            System.out.println(propertiesSet.toString());
                            nt = new BigECSTuple(qe, propertiesSet.get(qe.predicate.toString()), getQueryTriplePattern(qe));
                            System.out.println(nt.toString());
                        }
                        else{
                            TripleAsInt tai = getQueryTriplePattern(qe);
                            nt = new BigECSTuple(qe, tai.p, tai);
                        }
                        //BigECSTuple nt = new BigECSTuple(qe, propertiesSet.get(pr), getQueryTriplePattern(qe));

                        nt.subjectBinds = qe.subjectBinds;
                        nt.objectBinds = qe.objectBinds;
                        qlistTuple.add(nt);
                    }

                    boolean fl = false;

                    for(BigExtendedCharacteristicSet ecs1 : ecsLinks.keySet()){

                        visited = new HashSet<BigExtendedCharacteristicSet>();
                        if(findDataPatterns(ecs1, ecsLinks, qlistTuple, qlistTuple,
                                new ArrayList<BigECSTuple>())){
                            fl = true;
                        }

                    }
                }
                System.out.println("size of query patterns " + queryAnswerListSet2.size());

                HashSet<BigExtendedCharacteristicSet> queryECSs = ecsq.ecsSet;
                HashMap<BigECSTuple, HashSet<BigECSTuple>> queryECStoData = new HashMap<BigECSTuple, HashSet<BigECSTuple>>();
                for(BigExtendedCharacteristicSet qECS : queryECSs){
                    for(BigExtendedCharacteristicSet ecs : ecsIntegerMap.keySet()){
                        BigECSTuple qt ;
                        if(qECS.predicate.isURI())
                            qt = new BigECSTuple(qECS, propertiesSet.get(qECS.predicate.toString()), getQueryTriplePattern(qECS));
                        else{
                            TripleAsInt tai = getQueryTriplePattern(qECS);
                            qt = new BigECSTuple(qECS, tai.p, tai);
                        }
                        BitSet b1 = (BitSet) qt.ecs.subjectCS.longRep.clone();
                        BitSet b2 = (BitSet) ecs.subjectCS.longRep.clone();
                        BitSet b3 = (BitSet) qt.ecs.subjectCS.longRep.clone();
                        b1.and(b2);
                        if(!b1.equals(b3)) continue;
                        if(qt.ecs.objectCS != null && ecs.objectCS != null){
                            BitSet b4 = (BitSet) qt.ecs.objectCS.longRep.clone();
                            BitSet b5 = (BitSet) ecs.objectCS.longRep.clone();
                            BitSet b6 = (BitSet) qt.ecs.objectCS.longRep.clone();
                            b4.and(b5);
                            if(!b4.equals(b6)) continue;
                        }

                        if(qt.ecs.objectCS == null && ecs.objectCS != null) continue;
                        if(qt.ecs.objectCS != null && ecs.objectCS == null) continue;

                        if(!propIndexMap.get(ecsIntegerMap.get(ecs)).containsKey(propertiesSet.get(qt.ecs.predicate.toString())))
                            continue;
                        HashSet<BigECSTuple> dset = queryECStoData.getOrDefault(qt, new HashSet<BigECSTuple>());
                        BigECSTuple ecsT = new BigECSTuple(ecs, propertiesSet.get(qt.ecs.predicate.toString()), getQueryTriplePattern(qt.ecs));
                        ecsT.subjectBinds = qECS.subjectBinds;
                        //System.out.println(ecsT.subjectBinds.toString());
                        dset.add(ecsT);
                        queryECStoData.put(qt, dset);
                    }
                }
                //System.out.println(queryECStoData.toString());
		 		/*ArrayList<BigECSTuple> planList = new ArrayList<BigECSTuple>();

		 		for(BigECSTuple queryTuple : queryECStoData.keySet()){

		 			for(BigECSTuple queryTupleInner : queryECStoData.keySet()){

		 				if(queryTuple==queryTupleInner) continue;

		 				queryTuple.card += ecsLongArrayMap.get(ecsIntegerMap.get(queryTupleInner.ecs)).length;
		 				if(queryTuple.triplePattern.s == queryTupleInner.triplePattern.s){

		 				}

			 		}

		 			planList.add(queryTuple);

		 		}
		 		Collections.sort(planList, new BigECSTupleComparator2());

		 		BigECSTuple qt = planList.get(0);

		 		planList.remove(qt);



		 		for(BigECSTuple dt : queryECStoData.get(qt)){

		 		}*/



                varIndexMap = new HashMap<Node, Integer>();
                int varIndex = 0;

                for(BigQueryPattern key : queryAnswerListSet2.keySet()){
                    for(ArrayList<BigECSTuple> dataPattern : queryAnswerListSet2.get(key)){
                        for(BigECSTuple BigECSTuple : dataPattern){
                            TripleAsInt tai = BigECSTuple.triplePattern;
		 					/*if(!outerBindings.containsKey(reverseVarMap.get(tai.s)))
		 	 					outerBindings.put(reverseVarMap.get(tai.s), new HashSet<Long>());
		 						if(!outerBindings.containsKey(reverseVarMap.get(tai.o)))
		 							outerBindings.put(reverseVarMap.get(tai.o), new HashSet<Long>());*/
                            if(!varIndexMap.containsKey(reverseVarMap.get(tai.s)))
                                varIndexMap.put(reverseVarMap.get(tai.s), varIndex++);
                            if(!varIndexMap.containsKey(reverseVarMap.get(tai.o)))
                                varIndexMap.put(reverseVarMap.get(tai.o), varIndex++);
                        }
                    }
                }
                HashMap<ArrayList<BigExtendedCharacteristicSet>, HashSet<Integer>> qpVarMap = new HashMap<ArrayList<BigExtendedCharacteristicSet>, HashSet<Integer>>();
                HashMap<ArrayList<BigECSTuple>, HashSet<Integer>> qpVarMap2 = new HashMap<ArrayList<BigECSTuple>, HashSet<Integer>>();
                //HashMap<ArrayList<BigExtendedCharacteristicSet>, HashSet<ArrayList<BigExtendedCharacteristicSet>>> skipping = new HashMap<ArrayList<BigExtendedCharacteristicSet>, HashSet<ArrayList<BigExtendedCharacteristicSet>>>();
                HashMap<BigQueryPattern, HashSet<ArrayList<BigExtendedCharacteristicSet>>> skipping = new HashMap<BigQueryPattern, HashSet<ArrayList<BigExtendedCharacteristicSet>>>();
                HashMap<BigQueryPattern, HashSet<ArrayList<BigECSTuple>>> skipping2 = new HashMap<BigQueryPattern, HashSet<ArrayList<BigECSTuple>>>();
                for(BigQueryPattern BigQueryPattern : queryAnswerListSet2.keySet()){
                    int count = 0;

                    skipping.put(BigQueryPattern, new HashSet<ArrayList<BigExtendedCharacteristicSet>>());
                    skipping2.put(BigQueryPattern, new HashSet<ArrayList<BigECSTuple>>());
                    for(ArrayList<BigECSTuple> dataPattern : queryAnswerListSet2.get(BigQueryPattern)){
                        if(dataPattern.size() > 1){
                            //System.out.println("data pattern: " + dataPattern.toString());
                            boolean cont = false;
                            ArrayList<BigExtendedCharacteristicSet> dataPatECS = new ArrayList<BigExtendedCharacteristicSet>();
                            for(BigECSTuple et : dataPattern)
                                dataPatECS.add(et.ecs);
                            for(Vector<BigExtendedCharacteristicSet> vector : ecsVectors){
                                if(vector.containsAll(dataPatECS)){
                                    cont = true;
                                    break;
                                }
                            }
                            if(!cont){
                                count++;
                                skipping.get(BigQueryPattern).add(dataPatECS);
                                skipping2.get(BigQueryPattern).add(dataPattern);
                            }
                        }

                        HashSet<Integer> vars = new HashSet<Integer>();
                        for(BigECSTuple et : dataPattern){
                            TripleAsInt tai = et.triplePattern;
                            //if(reverseVarMap.get(tai.s).isVariable()){
                            if(tai.s < 0){
                                vars.add(varIndexMap.get(reverseVarMap.get(tai.s)));
                            }
                            if(tai.o < 0){
                                vars.add(varIndexMap.get(reverseVarMap.get(tai.o)));
                            }
                        }

                        qpVarMap2.put(dataPattern, vars);
                        qpVarMap2.put(BigQueryPattern.queryPattern, vars);

                    }

                    System.out.println("not contains all: " + count);
                    System.out.println("from total: " + queryAnswerListSet2.get(BigQueryPattern).size());

                }
                for(int i = 0; i < 10; i++){
                    ArrayList<BigQueryPattern> f = new ArrayList<>(queryAnswerListSet2.keySet());

                    Collections.sort(f, new BigECSTupleComparator());

                    HashMap<BigQueryPattern, HashMap<BigECSTuple, BigAnswerPattern>> qans = new HashMap<BigQueryPattern, HashMap<BigECSTuple, BigAnswerPattern>>();

                    HashMap<BigQueryPattern, HashMap<BigECSTuple, BigAnswerPattern>> qansReverse = new HashMap<BigQueryPattern, HashMap<BigECSTuple, BigAnswerPattern>>();

                    for(BigQueryPattern BigQueryPattern : f){

                        qans.put(BigQueryPattern, new HashMap<BigECSTuple, BigAnswerPattern>());

                        qansReverse.put(BigQueryPattern, new HashMap<BigECSTuple, BigAnswerPattern>());

                        if(BigQueryPattern.queryPattern.size() == 1 )
                        {

                            for(ArrayList<BigECSTuple> dataPattern1 : queryAnswerListSet2.get(BigQueryPattern)){
                                BigAnswerPattern ans = new BigAnswerPattern(dataPattern1.get(0), BigQueryPattern);
                                if(qans.get(BigQueryPattern).containsKey(ans.root)){
                                    ans = qans.get(BigQueryPattern).get(ans.root);
                                }
                                qans.get(BigQueryPattern).put(ans.root, ans);
                                qansReverse.get(BigQueryPattern).put(ans.root, ans);

                            }

                            continue;
                        }


                        for(ArrayList<BigECSTuple> dataPattern1 : queryAnswerListSet2.get(BigQueryPattern)){

                            for(int i1 = 0 ; i1 < dataPattern1.size()-1; i1++){

                                BigAnswerPattern ans = new BigAnswerPattern(dataPattern1.get(i1), BigQueryPattern);
                                if(qans.get(BigQueryPattern).containsKey(ans.root)){
                                    ans = qans.get(BigQueryPattern).get(ans.root);
                                }
                                BigAnswerPattern nextAns = new BigAnswerPattern(dataPattern1.get(i1+1), BigQueryPattern);
                                if(qans.get(BigQueryPattern).containsKey(nextAns.root)){
                                    nextAns = qans.get(BigQueryPattern).get(nextAns.root);
                                }
                                ans.addChild(nextAns);

                                qans.get(BigQueryPattern).put(ans.root, ans);
                                //qans.get(BigQueryPattern).put(nextAns.root, nextAns);

                            }
                            for(int i1 = dataPattern1.size()-1 ; i1 >= 1; i1--){

                                BigAnswerPattern ans = new BigAnswerPattern(dataPattern1.get(i1), BigQueryPattern);
                                if(qansReverse.get(BigQueryPattern).containsKey(ans.root)){
                                    ans = qansReverse.get(BigQueryPattern).get(ans.root);
                                }
                                BigAnswerPattern nextAns = new BigAnswerPattern(dataPattern1.get(i1-1), BigQueryPattern);
                                if(qansReverse.get(BigQueryPattern).containsKey(nextAns.root)){
                                    nextAns = qansReverse.get(BigQueryPattern).get(nextAns.root);
                                }
                                ans.addChild(nextAns);

                                qansReverse.get(BigQueryPattern).put(ans.root, ans);
                                //qans.get(BigQueryPattern).put(nextAns.root, nextAns);

                            }

                        }


                    }

                    HashMap<BigQueryPattern, HashSet<BigQueryPattern>> joinedBigQueryPatterns = new HashMap<BigQueryPattern, HashSet<BigQueryPattern>>();
                    HashMap<BigQueryPattern, HashSet<ArrayList<Integer>>> commonVarsMap = new HashMap<BigQueryPattern, HashSet<ArrayList<Integer>>>();
                    for(BigQueryPattern BigQueryPatterns1 : f){
                        commonVarsMap.put(BigQueryPatterns1, new HashSet<ArrayList<Integer>>());
                        joinedBigQueryPatterns.put(BigQueryPatterns1, new HashSet<BigQueryPattern>());
                    }
                    for(BigQueryPattern BigQueryPatterns1 : f){
                        for(BigQueryPattern BigQueryPatterns2 : f){
                            if(BigQueryPatterns1 == BigQueryPatterns2) continue;

                            HashSet<Integer> vars = qpVarMap2.get(BigQueryPatterns1.queryPattern);
                            HashSet<Integer> previousVars = qpVarMap2.get(BigQueryPatterns2.queryPattern);
                            ArrayList<Integer> commonVars = new ArrayList<Integer>();
                            for(Integer var : vars){
                                if(previousVars.contains(var)){
                                    commonVars.add(var);
                                }
                            }
                            if(commonVars.isEmpty()) continue;
                            commonVarsMap.get(BigQueryPatterns1).add(commonVars);
                            commonVarsMap.get(BigQueryPatterns2).add(commonVars);
                            joinedBigQueryPatterns.get(BigQueryPatterns1).add(BigQueryPatterns2);
                            joinedBigQueryPatterns.get(BigQueryPatterns2).add(BigQueryPatterns1);
                        }

                    }

                    HashMap<Long, Vector<Integer>> previous_res_vectors = null;

                    HashMap<Long, ArrayList<Vector<Integer>>> previous_res_vectors_new = new HashMap<Long, ArrayList<Vector<Integer>>>();

                    tstart = System.currentTimeMillis();

                    Map<BigQueryPattern, HashMap<Integer, Integer>> qpVarIndexMap = new HashMap<BigQueryPattern, HashMap<Integer,Integer>>();

                    for(int qi = 0; qi < f.size(); qi++){

                        BigQueryPattern qp = f.get(qi);

                        int nextIndex = 0;
                        HashMap<Integer, Integer> varIndexes = new HashMap<Integer, Integer>();
                        for(BigECSTuple nextECSPattern : qp.queryPattern){

                            if(!varIndexes.containsKey(nextECSPattern.triplePattern.s))
                                varIndexes.put(nextECSPattern.triplePattern.s, nextIndex++);
                            varIndexes.put(nextECSPattern.triplePattern.o, nextIndex++);

                        }
                        qpVarIndexMap.put(qp, varIndexes);
                        //System.out.println(varIndexes.toString());

                    }
                    //HashMap<String, Vector>
                    for(int qi = 0; qi < f.size(); qi++){

                        BigQueryPattern qp = f.get(qi);

                        tot = 0;

                        HashMap<Long, Vector<Integer>> res_vectors = new HashMap<Long, Vector<Integer>>();

                        HashMap<Integer, Integer> varIndexes = qpVarIndexMap.get(qp);
                        List<Integer> indexesOfCommonVarsToHash = new ArrayList<Integer>();

                        if(qi < f.size()-1){
                            BigQueryPattern nextQp = f.get(qi+1);
                            HashMap<Integer, Integer> nextVarIndexes = qpVarIndexMap.get(nextQp);

                            for(Integer nextVarIndex : varIndexes.keySet()){
                                if(nextVarIndexes.containsKey(nextVarIndex)){
                                    indexesOfCommonVarsToHash.add(varIndexes.get(nextVarIndex));
                                }
                            }
                        }

                        List<Integer> indexesOfCommonVarsToProbe = new ArrayList<Integer>();

                        if(qi > 0){
                            BigQueryPattern previousQp = f.get(qi-1);
                            HashMap<Integer, Integer> previousVarIndexes = qpVarIndexMap.get(previousQp);

                            for(Integer nextVarIndex : varIndexes.keySet()){
                                if(previousVarIndexes.containsKey(nextVarIndex)){
                                    indexesOfCommonVarsToProbe.add(varIndexes.get(nextVarIndex));
                                }
                            }
                        }
                        //System.out.println("indexes of common vars to hash: " + indexesOfCommonVarsToHash.toString());
                        //System.out.println("indexes of common vars to probe: " + indexesOfCommonVarsToProbe.toString());
                        HashSet<Vector<Integer>> results = null ;
                        HashMap<Long, ArrayList<Vector<Integer>>> res_vectors_new = new HashMap<Long, ArrayList<Vector<Integer>>>();
                        if(qp.queryPattern.size() > 1)
                            for(int k = 0; k < qp.queryPattern.size()-1; k++){
                                results = new HashSet<Vector<Integer>>();
                                boolean isLast = false;
                                if((k+1) == qp.queryPattern.size()-1) isLast = true;
                                BigECSTuple leftQueryECS = qp.queryPattern.get(k);
                                BigECSTuple rightQueryECS = qp.queryPattern.get(k+1);
                                HashSet<BigECSTuple> leftDataSet = queryECStoData.get(leftQueryECS);
                                HashSet<BigECSTuple> rightDataSet = queryECStoData.get(rightQueryECS);

                                if(previous_res_vectors_new != null){
                                    res_vectors_new = previous_res_vectors_new;

                                    joinTwoECSNew(res_vectors_new, leftDataSet, rightDataSet, indexesOfCommonVarsToHash, results,
                                            indexesOfCommonVarsToProbe, isLast );

                                }
			 				/*if(isLast){

			 					res_vectors_new = null;
			 				}*/

			 				/*if(indexesOfCommonVarsToHash.size() == 2){
			 					for(Vector<Integer> r : results){
			 						res_vectors.put(szudzik(r.get(indexesOfCommonVarsToHash.get(0)), r.get(indexesOfCommonVarsToHash.get(1))), r);
			 					}
			 				}
			 				else if(indexesOfCommonVarsToHash.size() == 1){
			 					for(Vector<Integer> r : results){
			 						res_vectors.put((long)r.get(indexesOfCommonVarsToHash.get(0)), r);
			 					}
			 				}*/
                                //System.out.println(k+" : " + res_vectors_new.size());

                            }
                        else{
                            results = new HashSet<Vector<Integer>>();
                            BigECSTuple leftQueryECS = qp.queryPattern.get(0);
                            HashSet<BigECSTuple> leftDataSet = queryECStoData.get(leftQueryECS);
                            if(previous_res_vectors_new != null){
                                res_vectors_new = previous_res_vectors_new;

                                joinOneECSNew(res_vectors_new, leftDataSet, indexesOfCommonVarsToHash, indexesOfCommonVarsToProbe, results);
                                //System.out.println(res_vectors_new.size());
                            }

			 				/*if(indexesOfCommonVarsToHash.size() == 2){
			 					for(Vector<Integer> r : results){
			 						res_vectors.put(szudzik(r.get(indexesOfCommonVarsToHash.get(0)), r.get(indexesOfCommonVarsToHash.get(1))), r);
			 					}
			 				}
			 				else if(indexesOfCommonVarsToHash.size() == 1){
			 					for(Vector<Integer> r : results){
			 						res_vectors.put((long)r.get(indexesOfCommonVarsToHash.get(0)), r);
			 					}
			 				}*/
                        }
                        //HashSet<Vector<Integer>> result
			 			/*results.clear();
			 			for(Integer what : res_vectors_new.keySet()){
			 				results.addAll(res_vectors_new.get(what));
			 			}*/
                        System.out.println("result size: " + results.size());
			 			/*for(Vector<Integer> v : results){
			 				System.out.println(v.toString());
			 			}*/
                        previous_res_vectors_new = res_vectors_new;
                        if(true)continue;
                        for(ArrayList<BigECSTuple> next : queryAnswerListSet2.get(qp)){

                            if(next.size() == 1){

                                if(previous_res_vectors == null)
                                    joinTwoECS(res_vectors, next.get(0), null, indexesOfCommonVarsToHash);
                                else
                                    joinTwoECS(res_vectors, previous_res_vectors, next.get(0), null,indexesOfCommonVarsToHash, indexesOfCommonVarsToProbe);
                            }
                            else

                                for(int ind = 0; ind < next.size()-1; ind++){

                                    if(previous_res_vectors == null)
                                        joinTwoECS(res_vectors, next.get(ind),next.get(ind+1), indexesOfCommonVarsToHash);
                                    else
                                        joinTwoECS(res_vectors, previous_res_vectors, next.get(ind),next.get(ind+1),indexesOfCommonVarsToHash, indexesOfCommonVarsToProbe);

                                }

                        }

                        previous_res_vectors = res_vectors;
                    }
                    tend = System.currentTimeMillis();

//                    if(previous_res_vectors != null)
//                        System.out.println("join " + previous_res_vectors.size() + "\t: " + (tend-tstart));
//                    else
//                        System.out.println("join 0 \t: " + (tend-tstart));
                    times.add((tend-tstart));


                }
                Collections.sort(times);

                System.out.println("best time: " + times.get(0));
            }
            catch(Exception e){e.printStackTrace();}
            //if(true) break;
//        }

        end1 = System.currentTimeMillis();
//        System.out.println("查询时间: " + (end1 - start1));
//        System.out.println();
        return (end1 - start1);
//        db.close();
    }

    private static void initSet() {
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent309", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent309", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent309"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent309", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent309", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent309@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent309", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent309", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent309", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course26"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course18"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent355"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course30"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/FullProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course34"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent355@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent355", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication13", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication13", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication13"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication13", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor1"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication13", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent101"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent180", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent180", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent180"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent180", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent180", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent180@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent180", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent180", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course26"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent180", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course13"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent180", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent180", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course44"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssistantProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#TeachingAssistant"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teachingAssistantOf", "http://www.Department13.University20.edu/Course18"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent14@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse26"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University633.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse45"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent102", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent102", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent102"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent102", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent102", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent102@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent102", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent102", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course34"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent102", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent102", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/FullProfessor4"));
        input.add(new RDFTriple("http://www.University771.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication12", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication12", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication12"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication12", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor6"));
        input.add(new RDFTriple("http://www.University487.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication9", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent13"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent85"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent43", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent43", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent43"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent43", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent43", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent43@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent43", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent43", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course35"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent43", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course42"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent43", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course21"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent43", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course26"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent60@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse28"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#TeachingAssistant"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse48"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teachingAssistantOf", "http://www.Department13.University20.edu/Course14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University754.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent60", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent60"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor1/Publication4", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor1/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor1/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssistantProfessor1"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent323", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent323", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent323"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent323", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent323", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent323@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent323", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent323", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent323", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent323", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course12"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent323", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse25", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateCourse"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse25", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateCourse25"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication12", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication12", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication12"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication12", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent11", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent11"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent11@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course11"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course22"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication11", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication11"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent299", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent299", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent299"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent299", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent299", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent299@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent299", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent299", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course28"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent299", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course25"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent299", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent299", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/Course44"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Lecturer5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/GraduateCourse48"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University83.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.University995.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Lecturer"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "Lecturer5@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.University347.edu"));
        input.add(new RDFTriple("http://www.University777.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication5", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent49"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent76"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication10", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication10", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication10", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication10", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent88"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication10", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent106"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent267", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent267", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent267"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent267", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent267", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent267@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent267", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent267", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course16"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent267", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course44"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent267", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course12"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent0@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course1"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course43"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.University683.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/Course34"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University193.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "AssistantProfessor5@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AssistantProfessor"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "AssistantProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#researchInterest", "Research4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.University536.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/GraduateCourse35"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Course14", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course"));
        input.add(new RDFTriple("http://www.University239.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication5", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent25"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent235", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent235", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent235"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent235", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent235", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent235@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent235", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent235", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course38"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent235", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course15"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent235", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication8", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication9", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent70"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent281", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent281", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent281"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent281", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent281", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent281@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent281", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent281", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course34"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent281", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course25"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent281", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course22"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent281", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course32"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5/Publication0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/Lecturer5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent92"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer5/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent98"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent203", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent203", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent203"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent203", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent203", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent203@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent203", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent203", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course16"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent203", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course44"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0/Publication4", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssistantProfessor0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent33"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent179", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent179", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent179"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent179", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent179", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent179@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent179", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent179", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course37"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent179", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent179", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course45"));
        input.add(new RDFTriple("http://www.University497.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent59", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent59", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent59"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent59", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent59", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent59@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent59", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent59", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse24"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent59", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse33"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent59", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University885.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent59", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent147", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent147", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent147"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent147", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent147", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent147@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent147", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent147", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent147", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course31"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent88", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent88", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent88"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent88", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent88", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent88@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent88", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent88", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course39"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent88", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course32"));
        input.add(new RDFTriple("http://www.University117.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor10/Publication9", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor10/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor10/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor10/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent38"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course34"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent193"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course44"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course12"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent193", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent193@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse42"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent27@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University440.edu"));
        input.add(new RDFTriple("http://www.University579.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent368", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent368", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent368"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent368", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent368", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent368@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent368", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent368", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course33"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent368", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course34"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent368", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course23"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent368", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/FullProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent115", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent115", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent115"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent115", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent115", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent115@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent115", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent115", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course28"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent115", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course15"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication5", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication5", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent57"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent56", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent56", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent56"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent56", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent56", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent56@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent56", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent56", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course36"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent56", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course15"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent56", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course19"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent73@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent73"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse31"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University857.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssistantProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse28"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent73", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/ResearchGroup1", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchGroup"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/ResearchGroup1", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#subOrganizationOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication16", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication16", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication16"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication16", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent336", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent336", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent336"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent336", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent336", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent336@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent336", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent336", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent336", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course36"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent336", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course11"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent336", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course12"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent161", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent161", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent161"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent161", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent161", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent161@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent161", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent161", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course33"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent161", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course45"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor1/Publication0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor1/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor1/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor1"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor1/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent41"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor1/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent52"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication8", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent72"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "AssociateProfessor3@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.University392.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/Course15"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University92.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/GraduateCourse15"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "AssociateProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/Course16"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#researchInterest", "Research25"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.University366.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AssociateProfessor"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse38", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateCourse"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse38", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateCourse38"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor2/Publication9", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor2/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor2/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor2/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent106"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer4/Publication0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer4/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer4/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/Lecturer4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer4/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent24", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent24", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent24"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent24", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent24", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent24@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent24", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent24", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent24", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course19"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent24", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent41", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent41", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent41"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent41", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent41", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent41@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent41", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent41", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse26"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent41", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University551.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent41", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor2"));
        input.add(new RDFTriple("http://www.University407.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent70", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent70", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent70"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent70", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent70", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent70@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent70", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent70", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent70", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course19"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent304", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent304", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent304"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent304", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent304", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent304@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent304", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent304", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course32"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent304", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course44"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent304", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course13"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent304", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course19"));
        input.add(new RDFTriple("http://www.University432.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication3", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent350", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent350", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent350"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent350", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent350", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent350@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent350", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent350", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course11"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent350", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course29"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication4", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor4/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor4"));
        input.add(new RDFTriple("http://www.University995.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.University615.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Course27", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Course27", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Course27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor6/Publication7", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor6/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor6/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor6/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent105"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse20", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateCourse"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse20", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateCourse20"));
        input.add(new RDFTriple("http://www.University198.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent248", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent248", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent248"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent248", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent248", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent248@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent248", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent248", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course43"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent248", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course35"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication16", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication16", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication16"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication16", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication16", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent66"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent294", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent294", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent294"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent294", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent294", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent294@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent294", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent294", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course35"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent294", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Course41", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Course41", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Course41"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent216", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent216", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent216"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent216", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent216", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent216@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent216", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent216", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course31"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent216", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course40"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent216", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent216", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Lecturer0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.University430.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Lecturer"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/Course38"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.University770.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "Lecturer0@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/GraduateCourse39"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University607.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent41"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent105"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication9", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor1"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent56"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent262", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent262", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent262"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent262", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent262", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent262@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent262", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent262", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent262", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course33"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent262", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course17"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer3/Publication0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer3/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer3/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/Lecturer3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#AssistantProfessor"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/Course27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/GraduateCourse26"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "AssistantProfessor0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/Course26"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.University753.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.University466.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University616.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#researchInterest", "Research10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "AssistantProfessor0@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.University905.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.University930.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent93"));
        input.add(new RDFTriple("http://www.University589.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.University772.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent230", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent230", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent230"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent230", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent230", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent230@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent230", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent230", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent230", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course45"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent230", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent230", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0/Publication4", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/Lecturer0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent17"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Lecturer0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent84"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication3", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication4", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University697.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse43"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse17"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent8@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent8", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication7", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent128", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent128", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent128"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent128", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent128", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent128@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent128", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent128", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course22"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent128", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent128", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course16"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent128", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course13"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent69", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent69", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent69"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent69", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent69", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent69@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent69", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent69", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course32"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent69", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course43"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent86", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent86", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent86"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent86", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent86", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent86@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent86", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent86", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse18"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent86", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University750.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent86", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/FullProfessor4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent86", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchAssistant"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication14", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor0/Publication14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent174", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent174", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent174"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent174", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent174", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent174@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent174", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent174", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course31"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent174", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course17"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent349", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent349", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent349"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent349", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent349", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent349@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent349", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent349", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent349", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course19"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication13", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication13", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication13"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication13", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication13", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent47"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent37"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssistantProfessor4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course41"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent37@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course13"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course29"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent37", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University339.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent54@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse28"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssistantProfessor2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent54"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse26"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchAssistant"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent54", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse17"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent142", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent142", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent142"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent142", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent142", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent142@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent142", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent142", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course41"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent142", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course20"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent142", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course32"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent142", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course23"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent83", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent83", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent83"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent83", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent83", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent83@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent83", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent83", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent83", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course45"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent83", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course1"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent83", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent317", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent317", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent317"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent317", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent317", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent317@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent317", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent317", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course21"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent317", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course23"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent317", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course32"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent317", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse19", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateCourse"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse19", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateCourse19"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor10/Publication4", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor10/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor10/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor10/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent73"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor10/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent82"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication9", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication9", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor0"));
        input.add(new RDFTriple("http://www.University366.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent22", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent22", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent22"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent22", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent22", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent22@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent22", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent22", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse37"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent22", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University394.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent22", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent22", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchAssistant"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent363", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent363", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent363"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent363", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent363", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent363@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent363", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent363", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent363", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course24"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent363", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course28"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent110", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent110", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent110"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent110", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent110", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent110@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent110", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent110", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course28"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent110", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course22"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent110", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication0", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent38"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor5/Publication0", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent101"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent51", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent51", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent51"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent51", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent51", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent51@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent51", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent51", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course29"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent51", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent51", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent51", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication11", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication11"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent35"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor9/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent84"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent331", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent331", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent331"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent331", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent331", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent331@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent331", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent331", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent331", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course20"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent331", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication3", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent76"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor2/Publication4", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor2/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor2/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse33", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateCourse"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse33", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateCourse33"));
        input.add(new RDFTriple("http://www.University473.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.University574.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5/Publication7", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssistantProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent41"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor4/Publication7", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor4/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor4/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor4"));
        input.add(new RDFTriple("http://www.University88.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent229", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent229", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent229"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent229", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent229", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent229@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent229", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent229", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course31"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent229", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course1"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/Course8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#FullProfessor"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#researchInterest", "Research25"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University883.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#doctoralDegreeFrom", "http://www.University487.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "FullProfessor6@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/GraduateCourse9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teacherOf", "http://www.Department13.University20.edu/Course9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "FullProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#mastersDegreeFrom", "http://www.University921.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent275", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent275", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent275"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent275", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent275", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent275@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent275", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent275", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course44"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent275", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course38"));
        input.add(new RDFTriple("http://www.University301.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor7/Publication2", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor7/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor7/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssistantProfessor7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor7/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent19"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor7/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent49"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Course22", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Course22", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Course22"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor6/Publication2", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor6/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor6/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor6/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent69"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication12", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication12", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication12"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication12", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication12", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent243", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent243", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent243"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent243", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent243", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent243@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent243", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent243", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course42"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent243", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent243", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course18"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent243", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course24"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication11", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication11"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent31"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent76"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor8/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent84"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent211", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent211", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent211"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent211", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent211", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent211@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent211", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent211", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent211", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course28"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent211", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course44"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent211", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course39"));
        input.add(new RDFTriple("http://www.University193.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication11", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication11"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent100"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication11", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent103"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication4", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor1/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor1"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse43"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University772.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse33"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent99"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#teachingAssistantOf", "http://www.Department13.University20.edu/Course8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent99@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent99", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#TeachingAssistant"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication10", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication10", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor7/Publication10", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor4/Publication7", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor4/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor4/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssistantProfessor4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent187", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent187", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent187"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent187", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent187", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent187@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent187", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent187", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course24"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent187", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication7", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor3/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication19", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication19", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication19"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication19", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor6/Publication19", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent75"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent109", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent109", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent109"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent109", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent109", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent109@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent109", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent109", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course17"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent109", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course1"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent109", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssistantProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent67", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent67", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent67"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent67", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent67", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent67@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent67", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent67", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent67", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University116.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent67", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/FullProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent155", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent155", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent155"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent155", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent155", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent155@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent155", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent155", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent155", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course22"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent155", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/FullProfessor1"));
        input.add(new RDFTriple("http://www.University48.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor1/Publication10", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor1/Publication10", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication10"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor1/Publication10", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor1"));
        input.add(new RDFTriple("http://www.University616.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University407.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse47"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse26"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent3@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent3", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course16"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent96@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/FullProfessor4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent96"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course37"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course29"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent96", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#ResearchAssistant"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssistantProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University625.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent35@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse45"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent35"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse42"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent35", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.University458.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor6/Publication2", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor6/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor6/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssistantProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication19", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication19", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication19"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication19", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent18", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent18", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent18"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent18", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent18", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent18@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent18", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent18", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course37"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent18", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course22"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent18", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent18", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/FullProfessor6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication2", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor5/Publication2", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent39"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent123", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent123", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent123"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent123", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent123", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent123@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent123", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent123", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course24"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent123", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course30"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent123", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course45"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent64", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent64", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent64"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent64", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent64", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent64@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent64", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent64", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course24"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent64", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course20"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent81", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent81", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateStudent81"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent81", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent81", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "GraduateStudent81@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent81", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent81", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/GraduateCourse24"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent81", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#undergraduateDegreeFrom", "http://www.University809.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateStudent81", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor5"));
        input.add(new RDFTriple("http://www.University824.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor3/Publication6", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor3/Publication6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication6"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor3/Publication6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssistantProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor3/Publication6", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent38"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent344", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent344", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent344"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent344", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent344", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent344@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent344", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent344", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course31"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent344", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course5"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent344", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course25"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent344", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse46", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateCourse"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse46", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateCourse46"));
        input.add(new RDFTriple("http://www.University691.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent32", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent32", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent32"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent32", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent32", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent32@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent32", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent32", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent32", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course27"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent32", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course23"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent32", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course38"));
        input.add(new RDFTriple("http://www.University210.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent7", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent7@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course37"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course40"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course36"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course17"));
        input.add(new RDFTriple("http://www.University128.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent312", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent312", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent312"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent312", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent312", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent312@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent312", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent312", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course45"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent312", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse14", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#GraduateCourse"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/GraduateCourse14", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "GraduateCourse14"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication4", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication4"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor0"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent31"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor0/Publication4", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent86"));
        input.add(new RDFTriple("http://www.University773.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication7", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication7"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssociateProfessor2/Publication7", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/AssociateProfessor2"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent288", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent288", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent288"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent288", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent288", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent288@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent288", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent288", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course9"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent288", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course15"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent288", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course41"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent288", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#advisor", "http://www.Department13.University20.edu/AssociateProfessor8"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Course35", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Course"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/Course35", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Course35"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication16", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication16", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "Publication16"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication16", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/FullProfessor3"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/FullProfessor3/Publication16", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#publicationAuthor", "http://www.Department13.University20.edu/GraduateStudent23"));
        input.add(new RDFTriple("http://www.University697.edu", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#University"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent256", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#UndergraduateStudent"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent256", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name", "UndergraduateStudent256"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent256", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#memberOf", "http://www.Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent256", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#emailAddress", "UndergraduateStudent256@Department13.University20.edu"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent256", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#telephone", "xxx-xxx-xxxx"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent256", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course37"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent256", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course39"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent256", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course36"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/UndergraduateStudent256", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#takesCourse", "http://www.Department13.University20.edu/Course24"));
        input.add(new RDFTriple("http://www.Department13.University20.edu/AssistantProfessor5/Publication2", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#Publication"));


    }

    public static TripleAsInt getQueryTriplePattern(BigExtendedCharacteristicSet queryLinks){
        int s = -1, p = -1, o = -1;
        if(!queryLinks.subject.isVariable()){
            if(intMap.containsKey(queryLinks.subject.getURI()))
                s = intMap.get(queryLinks.subject.getURI());
        }
        else{
            if(!varMap.containsKey(queryLinks.subject)){
                reverseVarMap.put(nextVar, queryLinks.subject);
                varMap.put(queryLinks.subject, nextVar--);
            }
            s = varMap.get(queryLinks.subject);
        }
        if(!queryLinks.predicate.isVariable()){
            if(intMap.containsKey(queryLinks.predicate.getURI()))
                p = propertiesSet.get(queryLinks.predicate.getURI());
        }
        else{
            if(!varMap.containsKey(queryLinks.predicate)){
                reverseVarMap.put(nextVar, queryLinks.predicate);
                varMap.put(queryLinks.predicate, nextVar--);
            }
            p = varMap.get(queryLinks.predicate);
        }
        if(!queryLinks.object.isVariable()){
            if(intMap.containsKey(queryLinks.object.toString()))
                o = intMap.get(queryLinks.object.toString());
        }
        else{
            if(!varMap.containsKey(queryLinks.object)){
                reverseVarMap.put(nextVar, queryLinks.object);
                varMap.put(queryLinks.object, nextVar--);
            }
            o = varMap.get(queryLinks.object);
        }

        return new TripleAsInt(s, p, o);
    }

    static public boolean findDataPatterns(BigExtendedCharacteristicSet ecs,
                                           Map<BigExtendedCharacteristicSet, HashSet<BigExtendedCharacteristicSet>> links,
                                           ArrayList<BigECSTuple> queryLinks,
                                           ArrayList<BigECSTuple> originalQueryLinks,
                                           ArrayList<BigECSTuple> list){

        if(queryLinks.size() == 0) {
            if(queryAnswerListSet2.containsKey(new BigQueryPattern(originalQueryLinks)))
                queryAnswerListSet2.get(new BigQueryPattern(originalQueryLinks)).add(list);
            else{
                HashSet<ArrayList<BigECSTuple>> d = new HashSet<ArrayList<BigECSTuple>>();
                d.add(list);
                queryAnswerListSet2.put(new BigQueryPattern(originalQueryLinks),d);
            }
            return true;
        }

        BitSet b1 = (BitSet) queryLinks.get(0).ecs.subjectCS.longRep.clone();
        BitSet b2 = (BitSet) ecs.subjectCS.longRep.clone();
        BitSet b3 = (BitSet) queryLinks.get(0).ecs.subjectCS.longRep.clone();
        b1.and(b2);
        if(!b1.equals(b3)) return false;
        if(queryLinks.get(0).ecs.objectCS != null && ecs.objectCS != null){
            BitSet b4 = (BitSet) queryLinks.get(0).ecs.objectCS.longRep.clone();
            BitSet b5 = (BitSet) ecs.objectCS.longRep.clone();
            BitSet b6 = (BitSet)  queryLinks.get(0).ecs.objectCS.longRep.clone();
            b4.and(b5);
            if(!b4.equals(b6)) return false;
        }

		/*if((queryLinks.get(0).ecs.subjectCS.longRep & ecs.subjectCS.longRep) != queryLinks.get(0).ecs.subjectCS.longRep){

			return false;
		}
		if(queryLinks.get(0).ecs.objectCS != null && ecs.objectCS != null)
			if((queryLinks.get(0).ecs.objectCS.longRep & ecs.objectCS.longRep) != queryLinks.get(0).ecs.objectCS.longRep){

				return false;
			}*/
        if(queryLinks.get(0).ecs.objectCS == null && ecs.objectCS != null) return false;
        if(queryLinks.get(0).ecs.objectCS != null && ecs.objectCS == null) return false;

        if(!propIndexMap.get(ecsIntegerMap.get(ecs)).containsKey(propertiesSet.get(queryLinks.get(0).ecs.predicate.toString())))
            return false;

        if(visited.contains(ecs)){
            if(queryAnswerListSet2.containsKey(new BigQueryPattern(originalQueryLinks)))
                queryAnswerListSet2.get(new BigQueryPattern(originalQueryLinks)).add(list);
            else{
                HashSet<ArrayList<BigECSTuple>> d = new HashSet<ArrayList<BigECSTuple>>();
                d.add(list);
                queryAnswerListSet2.put(new BigQueryPattern(originalQueryLinks),d);
            }
        }
        visited.add(ecs);

        BigECSTuple BigECSTuple = new BigECSTuple(ecs, propertiesSet.get(queryLinks.get(0).ecs.predicate.toString()), getQueryTriplePattern(queryLinks.get(0).ecs));
        BigECSTuple.subjectBinds = queryLinks.get(0).ecs.subjectBinds;
        BigECSTuple.objectBinds = queryLinks.get(0).ecs.objectBinds;

        list.add(BigECSTuple);

        if(!links.containsKey(ecs)){

            if(queryAnswerListSet2.containsKey(new BigQueryPattern(originalQueryLinks)))
                queryAnswerListSet2.get(new BigQueryPattern(originalQueryLinks)).add(list);
            else{
                HashSet<ArrayList<BigECSTuple>> d = new HashSet<ArrayList<BigECSTuple>>();
                d.add(list);
                queryAnswerListSet2.put(new BigQueryPattern(originalQueryLinks),d);
            }

            return true;
        }
        else{
            for(BigExtendedCharacteristicSet child : links.get(ecs)){

                if(queryLinks.size()>1){

                    ArrayList<BigECSTuple> dummy = new ArrayList<BigECSTuple>();
                    dummy.addAll(list);

                    findDataPatterns(child, links, new ArrayList<>(queryLinks.subList(1, queryLinks.size())), originalQueryLinks, dummy);
                }
                else {
                    if(queryAnswerListSet2.containsKey(new BigQueryPattern(originalQueryLinks)))
                        queryAnswerListSet2.get(new BigQueryPattern(originalQueryLinks)).add(list);
                    else{
                        HashSet<ArrayList<BigECSTuple>> d = new HashSet<ArrayList<BigECSTuple>>();
                        d.add(list);
                        queryAnswerListSet2.put(new BigQueryPattern(originalQueryLinks),d);
                    }

                }
            }
        }

        return false;

    }

    public static HashMap<Long, Vector<Integer>> joinTwoECS(HashMap<Long, Vector<Integer>> res,
                                                            HashMap<Long, Vector<Integer>> previous_res,
                                                            BigECSTuple e1, BigECSTuple e2,
                                                            List<Integer> hashIndexes,
                                                            List<Integer> probeIndexes
    ){


        if(e2 == null){
            long[] checkArr = csMap.get(ucs.get(e1.ecs.subjectCS));
            int p1 = propIndexMap.get(ecsIntegerMap.get(e1.ecs)).get(e1.property);
            long[] e1_array = ecsLongArrayMap.get(ecsIntegerMap.get(e1.ecs));
            //System.out.println(Arrays.toString(e1_array));
            for(int i = p1; i < e1_array.length; i++){

                long t = e1_array[i];
                if((int)((t >> 54)  & 0x3ff) != e1.property)
                    break;
                if(!checkBinds(e1, (int)(t >> 27 & 0x7FFFFFF), checkArr))
                    continue;

                Vector<Integer> v = new Vector<Integer>();
                v.add((int)((t >> 27) & 0x7FFFFFF));
                v.add((int)((t & 0x7FFFFFF)));
                //res.add(v);
                long hash = szudzik(v.get(probeIndexes.get(0)), v.get(probeIndexes.get(1)));
                if(previous_res.containsKey(hash)){
                    if(!hashIndexes.isEmpty())
                        res.put(szudzik(v.get(hashIndexes.get(0)), v.get(hashIndexes.get(1))), v);
                    else
                        res.put(hash, v);
                }
            }
        }
        else{

            HashMap<Integer, ArrayList<Vector<Integer>>> h = new HashMap<Integer, ArrayList<Vector<Integer>>>();

            //int p1 = propIndexMap.get(e1.ecs).get(e1.property);
            int p1 = propIndexMap.get(ecsIntegerMap.get(e1.ecs)).get(e1.property);
            long[] e1_array = ecsLongArrayMap.get(ecsIntegerMap.get(e1.ecs));
            for(int i = p1; i < e1_array.length; i++){
                Vector<Integer> v = new Vector<Integer>();
                long t = e1_array[i];
                if((int)((t >> 54)  & 0x3ff) != e1.property)
                    break;
                v.add((int)((t >> 27) & 0x7FFFFFF));
                v.add((int)((t & 0x7FFFFFF)));
                ArrayList<Vector<Integer>> l = h.getOrDefault((int)((t & 0x7FFFFFF)), new ArrayList<Vector<Integer>>());
                l.add(v);
                h.put((int)((t & 0x7FFFFFF)), l);
            }

            //int p2 = propIndexMap.get(e2.ecs).get(e2.property);
            int p2 = propIndexMap.get(ecsIntegerMap.get(e2.ecs)).get(e2.property);
            long[] e2_array = ecsLongArrayMap.get(ecsIntegerMap.get(e2.ecs));
            for(int i = p2; i < e2_array.length; i++){

                long t = e2_array[i];
                if((int)((t >> 54)  & 0x3ff) != e2.property)
                    break;
                if(h.containsKey((int)((t >> 27) & 0x7FFFFFF))){
                    ArrayList<Vector<Integer>> l = h.get((int)((t >> 27) & 0x7FFFFFF));
                    for(Vector<Integer> v : l){

                        Vector<Integer> nv = new Vector<Integer>(v);
                        nv.add((int)((t & 0x7FFFFFF)));
                        //res.add(nv);
                        if(hashIndexes.size() > 0)
                            res.put(szudzik(nv.get(hashIndexes.get(0)), nv.get(hashIndexes.get(1))), nv);
                        else
                            res.put(szudzik(nv.firstElement(), nv.lastElement()), nv);
                    }

                }

            }
        }

        return res;
    }

    public static HashMap<Long, ArrayList<Vector<Integer>>> joinTwoECSNew(HashMap<Long, ArrayList<Vector<Integer>>> res,
                                                                          HashSet<BigECSTuple> e1set, HashSet<BigECSTuple> e2set,
                                                                          List<Integer> hashIndexes,
                                                                          HashSet<Vector<Integer>> results,
                                                                          List<Integer> probeIndexes,
                                                                          boolean isLast
                                                                          //,HashMap<String, Vector<Integer>> outerJoin

    ){


        HashMap<Integer, ArrayList<Vector<Integer>>> h = new HashMap<Integer, ArrayList<Vector<Integer>>>();

        for(BigECSTuple e1 : e1set){

            int p1 = propIndexMap.get(ecsIntegerMap.get(e1.ecs)).get(e1.property);
            long[] e1_array = ecsLongArrayMap.get(ecsIntegerMap.get(e1.ecs));
            //System.out.println("e1: " + e1_array.length);
						/*long[] checkArr = csMap.get(ucs.get(e1.ecs.subjectCS));
						System.out.println(checkArr.length);*/
            for(int i = p1; i < e1_array.length; i++){

                long t = e1_array[i];

                if((int)((t >> 54)  & 0x3ff) != e1.property && e1.property >= 0)
                    break;

							/*if(!checkBinds(e1, (int)(t >> 27 & 0x7FFFFFF), checkArr))
								continue;*/
                if(!res.isEmpty()){

                    if(!res.containsKey((int)((t & 0x7FFFFFF)))){
                        //System.out.println("ASdasd");
                        //TODO
                        //an erxetai apo previous res, tote den prepei na koitaksei to object!!!
                        continue;
                    }
                    else{
                        ArrayList<Vector<Integer>> l = res.get((int)((t & 0x7FFFFFF)));
                        //for(Vector<Integer> nv : l){
                        ArrayList<Vector<Integer>> vg = new ArrayList<Vector<Integer>>();
                        for(int j = 0; j < l.size(); j++){
                            //Vector<Integer> v = new Vector<Integer>(nv);
                            Vector<Integer> nv = new Vector<Integer>(l.get(j));
                            //System.out.println(nv.size());
                            nv.add((int)((t & 0x7FFFFFF)));
                            //res.getOrDefault(v.lastElement(), new ArrayList<Vector<Integer>>());
                            vg.add(nv);

                        }
                        h.put((int)((t & 0x7FFFFFF)), vg);

                    }
                }
                else{
                    Vector<Integer> v = new Vector<Integer>();
                    v.add((int)((t >> 27) & 0x7FFFFFF));
                    v.add((int)((t & 0x7FFFFFF)));
                    ArrayList<Vector<Integer>> l = h.getOrDefault((int)((t & 0x7FFFFFF)), new ArrayList<Vector<Integer>>());
                    l.add(v);
                    h.put((int)((t & 0x7FFFFFF)), l);
                }

            }

        }

					/*if(isLast){
						HashSet<Integer> sizes = new HashSet<Integer>();
						for(Integer r : res.keySet()){
							for(Vector<Integer> vv : res.get(r))
								sizes.add(vv.size());
						}
						System.out.println("mesh" + sizes.toString());
					}*/
        //res.clear();
        for(BigECSTuple e2 : e2set){
            int p2 = propIndexMap.get(ecsIntegerMap.get(e2.ecs)).get(e2.property);
            long[] e2_array = ecsLongArrayMap.get(ecsIntegerMap.get(e2.ecs));
            //System.out.println("e2: " + e2_array.length);
            for(int i = p2; i < e2_array.length; i++){

                long t = e2_array[i];
                if((int)((t >> 54)  & 0x3ff) != e2.property)
                    break;

                if(h.containsKey((int)((t >> 27) & 0x7FFFFFF))){

                    ArrayList<Vector<Integer>> l = h.get((int)((t >> 27) & 0x7FFFFFF));
                    for(Vector<Integer> v : l){

                        Vector<Integer> nv = new Vector<Integer>(v);
                        nv.add((int)((t & 0x7FFFFFF)));


                        if(!isLast){
                            ArrayList<Vector<Integer>> vg = res.getOrDefault(nv.lastElement(),
                                    new ArrayList<Vector<Integer>>());

                            vg.add(nv);
                            res.put((long)nv.lastElement(), vg);
                        }
                        else{
                            //TODO
                            //edw prepei na doume poia hasharoume an yparxei epomeno query pattern, alliws
                            //kateftheian sta results
                            if(hashIndexes.size() == 0)
                                results.add(nv);
                            else{
                                if(hashIndexes.size()==1){
                                    ArrayList<Vector<Integer>> vg = res.getOrDefault(nv.get(hashIndexes.get(0)),
                                            new ArrayList<Vector<Integer>>());

                                    vg.add(nv);
                                    res.put((long)nv.get(hashIndexes.get(0)), vg);
                                }
                                else if(hashIndexes.size()==2){
                                    //System.out.println("Dfsdfsdf");
                                    ArrayList<Vector<Integer>> vg = res.getOrDefault(szudzik(nv.get(hashIndexes.get(0)), nv.get(hashIndexes.get(1))),
                                            new ArrayList<Vector<Integer>>());
                                    vg.add(nv);
                                    res.put( szudzik(nv.get(hashIndexes.get(0)), nv.get(hashIndexes.get(1))), vg);
                                }
                            }

                        }

                    }

                }

            }
        }
        //System.out.println("ffff " + res.size());
        return res;
    }

    public static HashMap<Long, ArrayList<Vector<Integer>>> joinOneECSNew(HashMap<Long, ArrayList<Vector<Integer>>> res,
                                                                          HashSet<BigECSTuple> e1set,
                                                                          List<Integer> hashIndexes,
                                                                          List<Integer> probeIndexes, HashSet<Vector<Integer>> results

    ){

        int s, o;
        long probe;
        for(BigECSTuple e1 : e1set){
            int p1 = propIndexMap.get(ecsIntegerMap.get(e1.ecs)).get(e1.property);
            long[] e1_array = ecsLongArrayMap.get(ecsIntegerMap.get(e1.ecs));
            //System.out.println("e1: " + e1_array.length);
            long[] checkArr = csMap.get(ucs.get(e1.ecs.subjectCS));
            //System.out.println(checkArr.length);
            for(int i = p1; i < e1_array.length; i++){

                long t = e1_array[i];
                if((int)((t >> 54)  & 0x3ff) != e1.property)
                    break;

                s = (int)((t >> 27) & 0x7FFFFFF);
                if(!checkBinds(e1, s, checkArr))
                    continue;
                o = (int)((t & 0x7FFFFFF));
                if(probeIndexes.size() > 0){
                    //System.out.println(probeIndexes.toString());
                    //szudzik((int)((t >> 27) & 0x7FFFFFF), (int)((t & 0x7FFFFFF)))
                    probe = -1l;
                    if(probeIndexes.size() == 1){
                        if(probeIndexes.get(0)==0)
                            probe = s;
                        else
                            probe = o;
                    }
                    else{
                        probe = szudzik(s,o);
                    }
                    if(!res.containsKey(probe)){
                        //System.out.println(res.toString());
                        //System.out.println(szudzik((int)((t >> 27) & 0x7FFFFFF), (int)((t & 0x7FFFFFF))));
                        continue;
                    }
                    for(Vector<Integer> vv : res.get(probe)){
                        //vv.add((int)((t >> 27) & 0x7FFFFFF));
                        //vv.add((int)((t & 0x7FFFFFF)));
                        //if(probeIndexes.size() == 0)
                        //System.out.println(vv.toString());
                        results.add(vv);
                    }
                    //System.out.println("-----");
                    continue;
                }
                else{
                    Vector<Integer> v = new Vector<Integer>();
                    v.add(s);
                    v.add(o);
                    //v.add();
                    //v.add((int)((t & 0x7FFFFFF)));
                    //if(probeIndexes.size() == 0)
                    results.add(v);
                }

            }
        }


        return res;
    }

    public static HashMap<Long, Vector<Integer>> joinTwoECS(HashMap<Long, Vector<Integer>> res,
                                                            BigECSTuple e1, BigECSTuple e2,
                                                            List<Integer> hashIndexes

    ){


        if(e2 == null){

            int p1 = propIndexMap.get(ecsIntegerMap.get(e1.ecs)).get(e1.property);
            long[] e1_array = ecsLongArrayMap.get(ecsIntegerMap.get(e1.ecs));
            long[] checkArr = csMap.get(ucs.get(e1.ecs.subjectCS));

            for(int i = p1; i < e1_array.length; i++){

                long t = e1_array[i];
                if((int)((t >> 54)  & 0x3ff) != e1.property)
                    break;
                if(!checkBinds(e1, (int)(t >> 27 & 0x7FFFFFF), checkArr))
                    continue;
                Vector<Integer> v = new Vector<Integer>();
                v.add((int)((t >> 27) & 0x7FFFFFF));
                v.add((int)((t & 0x7FFFFFF)));
                //res.add(v);
                if(!hashIndexes.isEmpty())
                    res.put(szudzik(v.get(hashIndexes.get(0)), v.get(hashIndexes.get(1))), v);
                else
                    res.put(szudzik(v.firstElement(), v.lastElement()), v);
            }
        }
        else{

            HashMap<Integer, ArrayList<Vector<Integer>>> h = new HashMap<Integer, ArrayList<Vector<Integer>>>();

            int p1 = propIndexMap.get(ecsIntegerMap.get(e1.ecs)).get(e1.property);
            long[] e1_array = ecsLongArrayMap.get(ecsIntegerMap.get(e1.ecs));
            for(int i = p1; i < e1_array.length; i++){
                Vector<Integer> v = new Vector<Integer>();
                long t = e1_array[i];
                if((int)((t >> 54)  & 0x3ff) != e1.property)
                    break;
                v.add((int)((t >> 27) & 0x7FFFFFF));
                v.add((int)((t & 0x7FFFFFF)));
                ArrayList<Vector<Integer>> l = h.getOrDefault((int)((t & 0x7FFFFFF)), new ArrayList<Vector<Integer>>());
                l.add(v);
                h.put((int)((t & 0x7FFFFFF)), l);
            }

            int p2 = propIndexMap.get(ecsIntegerMap.get(e2.ecs)).get(e2.property);
            long[] e2_array = ecsLongArrayMap.get(ecsIntegerMap.get(e2.ecs));
            for(int i = p2; i < e2_array.length; i++){

                long t = e2_array[i];
                if((int)((t >> 54)  & 0x3ff) != e2.property)
                    break;
                if(h.containsKey((int)((t >> 27) & 0x7FFFFFF))){
                    ArrayList<Vector<Integer>> l = h.get((int)((t >> 27) & 0x7FFFFFF));
                    for(Vector<Integer> v : l){

                        Vector<Integer> nv = new Vector<Integer>(v);
                        nv.add((int)((t & 0x7FFFFFF)));
                        //res.add(nv);
                        if(hashIndexes.size() == 2)
                            res.put(szudzik(nv.get(hashIndexes.get(0)), nv.get(hashIndexes.get(1))), nv);
								/*else if(hashIndexes.size() == 1)
									res.put(szudzik(nv.get(hashIndexes.get(0)), nv.get(hashIndexes.get(1))), nv);*/
                        else
                            res.put((long)nv.lastElement(), nv);

                    }

                }

            }
        }


        return res;
    }

    public static long szudzik(int a, int b){

        return a >= b ? a * a + a + b : a + b * b;

    }

    public static boolean checkBinds(BigECSTuple tuple, int subject, long[] array){

        if(tuple.subjectBinds != null){

            //System.out.println("asdasdasd");
            for(Integer prop : tuple.subjectBinds.keySet()){
                //System.out.println("sdfsdfs");
                int obj = tuple.subjectBinds.get(prop);
                long tripleSPOLong = ((long)prop << 54 |
                        (long)(subject & 0x7FFFFFF) << 27 |
                        (long)(obj & 0x7FFFFFF));
                if(indexOfTriple(array, tripleSPOLong) < 0) {
                    //System.out.println(tripleSPOLong);
                    //System.out.println(Arrays.toString(array));
                    return false;
                }
                //System.out.println(reverseIntMap.get(subject) + ", " + reverseIntMap.get(obj));
            }

        }
        return true;
    }

    public static int indexOfTriple(long[] a, long key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            long s = a[mid];
            if      (key < s ) hi = mid - 1;
            else if (key > s) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    static class BigECSTupleComparator implements Comparator<BigQueryPattern>
    {
        public int compare(BigQueryPattern c1, BigQueryPattern c2)
        {
            if(c1.queryPattern.size() == c2.queryPattern.size()){
                if(c1.boundVars > 0 && c2.boundVars > 0)
                    return (new Integer(c1.boundVars).compareTo(c2.boundVars));
                else if(c1.boundVars > 0 )
                    return -1;
                else if(c2.boundVars > 0 )
                    return +1;
                else
                    return 0;
            }

            else
                return -1*(new Integer(c1.queryPattern.size())).compareTo(c2.queryPattern.size());
        }
    }

    static class BigECSTupleComparator2 implements Comparator<BigECSTuple>
    {
        public int compare(BigECSTuple c1, BigECSTuple c2)
        {

            return (new Integer(c1.card)).compareTo(c2.card);
        }
    }

    static class ECSTupleComparator implements Comparator<QueryPattern>
    {
        public int compare(QueryPattern c1, QueryPattern c2)
        {
            if(c1.queryPattern.size() == c2.queryPattern.size()){
                if(c1.boundVars > 0 && c2.boundVars > 0)
                    return (new Integer(c1.boundVars).compareTo(c2.boundVars));
                else if(c1.boundVars > 0 )
                    return -1;
                else if(c2.boundVars > 0 )
                    return +1;
                else
                    return 0;
            }

            else
                return -1*(new Integer(c1.queryPattern.size())).compareTo(c2.queryPattern.size());
        }
    }
}
