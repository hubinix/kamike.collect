/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kamike.db.generic.FieldLength;
import com.kamike.db.generic.FieldName;
import com.kamike.db.generic.Id;
import com.kamike.db.generic.TableName;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Brin
 */
@TableName("fetch_description")
public class Description {

    private static final Gson gson = new Gson();

    @Id
    @FieldName("id")
    @FieldLength(64)
    protected String id;
    //词频，所有词的词频,是一个json

    @FieldName("counts")
    @FieldLength(16777216)
    protected String counts;//count的json

    protected List<Count> countList;

    protected List<Word> wordList;

    @FieldName("title")
    @FieldLength(255)
    protected String title;//标题

    @FieldName("words")
    @FieldLength(16777216)
    protected String words;//word的json

    @FieldName("content_id")
    @FieldLength(64)
    protected String contentId;

    @FieldName("create_date")
    protected Date createDate;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the contentId
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * @param contentId the contentId to set
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the countList
     */
    public List<Count> getCountList() {
        if (countList == null) {
            countList = gson.fromJson(counts, new TypeToken<ArrayList<Count>>() {
            }.getType());
        }
        return countList;
    }

    /**
     * @return the wordList
     */
    public List<Word> getWordList() {
        if (wordList == null) {
            wordList = gson.fromJson(words, new TypeToken<ArrayList<Count>>() {
            }.getType());
        }
        return wordList;
    }

    /**
     * @param countList the countList to set
     */
    public void setCountList(List<Count> countList) {
        this.countList = countList;
    }

    /**
     * @param wordList the wordList to set
     */
    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    /**
     * @return the counts
     */
    public String getCounts() {
        return counts;
    }

    /**
     * @param counts the counts to set
     */
    public void setCounts(String counts) {
        this.counts = counts;
    }

    /**
     * @return the words
     */
    public String getWords() {
        return words;
    }

    /**
     * @param words the words to set
     */
    public void setWords(String words) {
        this.words = words;
    }

}
