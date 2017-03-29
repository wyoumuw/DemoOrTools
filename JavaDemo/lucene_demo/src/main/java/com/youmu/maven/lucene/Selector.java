package com.youmu.maven.lucene;

import com.youmu.maven.utils.StringUtil;
import com.youmu.maven.utils.reflection.BeanUtil;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.SimpleTypeConverter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class Selector{

    /**
     *
     * 6.0后数字类型查找方式了，这里不做展示，用LongPoint.newExactQuery(field,Long.valueOf(criteria));获取Query
     *
     */
    public <T> List<T> queryTop(String field,String criteria,String storeDir,Class<T> clazz) throws Exception{
        List<T> list=new ArrayList<T>();
        QueryParser queryParser=new QueryParser(field,new StandardAnalyzer());
        IndexReader reader=DirectoryReader.open(FSDirectory.open(Paths.get(storeDir)));
        IndexSearcher searcher=new IndexSearcher(reader);
        Query q= queryParser.parse(criteria);
        TopDocs topDocs=searcher.search(q,10);
        for(int i=0;i<topDocs.totalHits;i++){
            ScoreDoc scoreDoc=topDocs.scoreDocs[i];
            Document doc=searcher.doc(scoreDoc.doc);

            List<IndexableField> fields=  doc.getFields();
            T t=clazz.newInstance();
            SimpleTypeConverter typeConverter=new SimpleTypeConverter();
            for (IndexableField indexableField:fields) {
                String name=indexableField.name();
                Field f=BeanUtil.getField(clazz,name);
                Method method= BeanUtil.getMethod(clazz, StringUtil.toSetterMethodName(name),f.getType());
                method.invoke(t,typeConverter.convertIfNecessary(indexableField.stringValue(),f.getType()));
            }
            list.add(t);
        }
        return list;
    }


}
