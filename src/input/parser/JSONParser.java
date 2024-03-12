/**
 * creates a FigureNode from a given JSON file
 * 
 * @authors Della, Jack, Sage 
 * @date 2/20/24
 */

package input.parser;

import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import input.components.*;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;
import input.exception.ParseException;

public class JSONParser
{	
	protected ComponentNode  _astRoot;

	/**
	 * default constructor
	 */
	public JSONParser()
	{
		_astRoot = null;
	}

	/**
	 * method that throws a ParseException
	 */
	private void error(String message)
	{
		throw new ParseException("Parse error: " + message);
	}

	/**
	 * creates a FigureNode from the information within the JSON file
	 * @param str (JSON file)
	 * @return FigureNode
	 * @throws ParseException if JSONException is thrown
	 */
	public ComponentNode parse(String str) throws ParseException
	{
		// Parsing is accomplished via the JSONTokenizer class.
		try { 
			JSONTokener tokenizer = new JSONTokener(str); 
			JSONObject  JSONroot = (JSONObject)tokenizer.nextValue();
			JSONObject jsonFigure = (JSONObject) JSONroot.get("Figure");

			String description = getDescription(jsonFigure);
			PointNodeDatabase pointsDatabase = getPointNodeDatabase(jsonFigure);
			SegmentNodeDatabase segmentsDatabase = getSegmentNodeDatabase(jsonFigure, pointsDatabase);

			return new FigureNode(description, pointsDatabase, segmentsDatabase); // replace with call to builder
		}
		catch (JSONException e) { error(""); }
		
		return null; //if an exception is thrown
	}

	/**
	 * retrieves the description from the JSON file
	 * @param figure
	 * @return String of description
	 */
	private String getDescription(JSONObject figure) 
	{
		return figure.getString("Description");
	}
	
	/**
	 * retrieves the points from the JSON file
	 * @param figure
	 * @return PointNodeDatabase of all the points
	 */
	private PointNodeDatabase getPointNodeDatabase(JSONObject figure) 
	{
		JSONArray jsonPoints = (JSONArray) figure.getJSONArray("Points");
		PointNodeDatabase pointsDatabase = new PointNodeDatabase(); // call to builder
		
		Iterator<Object> iterPoints = jsonPoints.iterator();
		int index = 0;
		while(iterPoints.hasNext())
		{
			JSONObject currPoint = jsonPoints.getJSONObject(index);
			
			String name = currPoint.getString("name");
			double x = currPoint.getDouble("x");
			double y = currPoint.getDouble("y");
			
			PointNode pn = new PointNode(name, x, y); // builder
			pointsDatabase.put(pn);
			
			iterPoints.next();
			index++;
		}
		
		return pointsDatabase;
	}
	
	/**
	 * retrieves the segments from the JSON file
	 * @param figure
	 * @return SegmentNodeDatabase of all the possible segments
	 */
	private SegmentNodeDatabase getSegmentNodeDatabase(JSONObject figure, PointNodeDatabase pointNodeDatabase) 
	{
		JSONArray jsonSegments = (JSONArray) figure.getJSONArray("Segments");
		SegmentNodeDatabase segmentDatabase = new SegmentNodeDatabase(); // builder
		
		int index = 0;
		Iterator<Object> iterSegments = jsonSegments.iterator();
		while(iterSegments.hasNext())
		{
			JSONObject curr = jsonSegments.getJSONObject(index);
			//the set only contains the key of curr
			Set<String> keySet = curr.keySet();
			
			String keyName = keySet.iterator().next();	//the key as a String from the set
			PointNode fromPN = pointNodeDatabase.getPoint(keyName);
			JSONArray adjList = curr.getJSONArray(keyName); //the values associated with the key
			
			//for every node connected to the key
			for(int i = 0; i < adjList.length(); i++) { 
				PointNode toPN = pointNodeDatabase.getPoint(adjList.getString(i));
				segmentDatabase.addUndirectedEdge(fromPN, toPN);
			}
			
			iterSegments.next();
			index++;
		}
		
		return segmentDatabase;
	}
}