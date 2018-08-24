package cn.xxyangyoulin.framework;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ActionManager {
    private Map<String, ActionMapping> allActions;

    public ActionMapping getActionMapping(String actionName) {
        if (actionName == null) {
            throw new RuntimeException("actionName == null");
        }

        ActionMapping actionMapping = allActions.get(actionName);

        if (actionMapping == null) {
            throw new RuntimeException("get actionMapping == null");
        }

        return actionMapping;
    }

    public ActionManager() {
        allActions = new HashMap<>();
        File file = new File("/a/Java_project/javaee_test_custom_mvc/target/javaee_test_custom_mvc/mvc-config.xml");
        System.out.println(file.length());
        System.out.println(file.getAbsoluteFile());

        init();

    }


    private void init() {
        try {
            SAXReader reader = new SAXReader();


            InputStream inputStream = this.getClass().getResourceAsStream("/mvc-config.xml");

            Document doc = reader.read(inputStream);
            Element root = doc.getRootElement();

            List<Element> listAction = root.elements("action");

            for (Element element : listAction) {
                ActionMapping actionMapping = new ActionMapping();
                actionMapping.setName(element.attributeValue("name"));
                actionMapping.setClassName(element.attributeValue("class"));
                actionMapping.setMethod(element.attributeValue("method"));

                Map<String, Result> results = new HashMap<String, Result>();

                Iterator<Element> it = element.elementIterator("result");
                while (it.hasNext()) {
                    Element eleResult = it.next();

                    Result result = new Result();
                    result.setName(eleResult.attributeValue("name"));
                    result.setType(eleResult.attributeValue("type"));
                    result.setPage(eleResult.getTextTrim());

                    results.put(result.getName(), result);
                }

                actionMapping.setResults(results);
                allActions.put(actionMapping.getName(), actionMapping);
            }

        } catch (Exception e) {
            throw new RuntimeException("mvc-config.xml error->", e);
        }
    }
}
