/**
 * Copyright 2011-2015 DEIB - Politecnico di Milano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Acknowledgements:
 * We would like to thank Davide Barbieri, Emanuele Della Valle,
 * Marco Balduini, Soheila Dehghanzadeh, Shen Gao, and
 * Daniele Dell'Aglio for the effort in the development of the software.
 *
 * This work is partially supported by
 * - the European LarKC project (FP7-215535) of DEIB, Politecnico di
 * Milano
 * - the ERC grant “Search Computing” awarded to prof. Stefano Ceri
 * - the European ModaClouds project (FP7-ICT-2011-8-318484) of DEIB,
 * Politecnico di Milano
 * - the IBM Faculty Award 2013 grated to prof. Emanuele Della Valle;
 * - the City Data Fusion for Event Management 2013 project funded
 * by EIT Digital of DEIB, Politecnico di Milano
 * - the Dynamic and Distributed Information Systems Group of the
 * University of Zurich;
 * - INSIGHT NUIG and Science Foundation Ireland (SFI) under grant
 * No. SFI/12/RC/2289
 */
package eu.larkc.csparql.cep.esper;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfSnapshot;

class QueryListener extends RdfSnapshot implements UpdateListener {

   final LinkedList<WinList> queue = new LinkedList<>();

   QueryListener(final String id) {
      super(id);
   }

   public void update(final EventBean[] newEvents, final EventBean[] oldEvents) {

//      final List<RdfQuadruple> quads = new ArrayList<>();
      System.out.println("当前流三元组个数: " + newEvents.length);
      if (newEvents == null)
    	  return;
      for (WinList list : queue) {
         list.time -= step;
      }
      while (queue.peek() != null && queue.peek().time <= 0) {
         queue.poll();
      }

      int capcity = 0;
      for (WinList list : queue) {
         capcity += list.quadrupleList.size();
      }
      if (newEvents.length < capcity)
         return;
      WinList winList = new WinList();
      winList.setTime(win);
      final List<RdfQuadruple> quads = new ArrayList<>();
      for (int i = capcity; i < newEvents.length; i++) {

         RdfQuadruple q = new RdfQuadruple(newEvents[i].get("subject").toString(), newEvents[i].get(
                 "predicate").toString(), newEvents[i].get("object").toString(), Long.parseLong(newEvents[i].get("timestamp").toString()));

//         System.out.println(q.toString());

         q.setStreamName(newEvents[i].get("streamName").toString());

         quads.add(q);
      }
      winList.setQuadrupleList(quads);
      queue.add(winList);
      setChanged();
      this.notifyObservers(quads);
   }

//      for (final EventBean b : newEvents) {
//         // TODO: to keep if we use Observer Observable in java.util.
//         // this.setChanged();
//         final RdfQuadruple q = new RdfQuadruple(b.get("subject").toString(), b.get(
//               "predicate").toString(), b.get("object").toString(), Long.parseLong(b.get("timestamp").toString()));
//
//         System.out.println(q.toString());
//
//         q.setStreamName(b.get("streamName").toString());
//
//         quads.add(q);
//      }
}
