package com.youmu.maven.lucene;

import com.youmu.maven.support.DocumentConverter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.util.CollectionUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class Indexer {

    public void createIndexs(List<?> objs,String storeDir,DocumentConverter covert) throws Exception {
        if(CollectionUtils.isEmpty(objs)){
            return;
        }
        List<Document> docs=new ArrayList<Document>();
        for (Object obj:objs){
            docs.add(covert.convert(obj));
        }
        IndexWriter writer=null;
        try {
            Analyzer analyzer = new StandardAnalyzer();
            Directory directory = FSDirectory.open(Paths.get(storeDir));
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig();
            writer = new IndexWriter(directory, indexWriterConfig);
            writer.addDocuments(docs);

        }finally {
            writer.close();
        }

    }

}
