package rough;

import data.payload;
import io.restassured.path.json.JsonPath;

public class JsonParse {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(payload.CoursePrice());

		int count = js.getInt("courses.size()");
		System.out.println(count);

		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);

		String titleFirstCourse = js.get("courses[0].title");
		System.out.println(titleFirstCourse);

		for (int i = 0; i < count; i++) {
			String courseTitle = js.get("courses[" + i + "].title");
			String coursePrice = js.get("courses[" + i + "].price").toString();
			System.out.println(courseTitle + ": " + coursePrice);
		}

		for (int j = 0; j < count; j++) {
			String courseTitle = js.get("courses[" + j + "].title");
			if (courseTitle.equalsIgnoreCase("RPA")) {
				String copies = js.get("courses[" + j + "].copies").toString();
				System.out.println("Copies Sold: " + copies);
				break;
			}
		}

		int totalAmount = 0;
		for (int k = 0; k < count; k++) {
			int coursePrice = js.get("courses[" + k + "].price");
			int copies = js.get("courses[" + k + "].copies");
			totalAmount = totalAmount + (copies * coursePrice);
		}
		System.out.println(totalAmount);
	}

}
