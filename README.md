# EmoWell

EmoWell is an AI Powered application designed to cater to the emotional well-being of individuals by allowing them to track their daily emotions, happy incidents, and sad incidents. The app analyses the data with AI and identifiess the key happiness and unpleasant experiences contributers and shows it in a dashboard with interactive charts.

## Features

- **Daily Emotion Log:** Users can log their emotions on a scale of 1 to 10, indicating how they are feeling each day.
- **Happy Incident Tracking:** Users can record one incident that made them happy during the day.
- **Sad Incident Tracking:** Users can record one incident that made them sad during the day.
- **Interactive Dashboard:** The dashboard provides visual representations of the emotional data.
  - **Line Graph:** Shows the trend of daily emotion scores over time.
  - **Happiness Donut Graph:** Illustrates the major contributors to happiness and their weightage.
  - **Sadness Donut Graph:** Illustrates the major contributors to sadness and their weightage.

## Technologies Used

- Frontend:
  - HTML, CSS, JavaScript
  - Chart.js library for interactive charts
  - Bootstrap for styling

- Backend:
  - Spring Boot framework with Java
  - MySQL database for data storage
  - Spring Data JPA for object-relational mapping
  - OpenAI API for data analysis

## Setup and Installation

1. Clone the repository:
2. Frontend:
- Open the `UI\input.html` file in a web browser.
- Change the constant `api_base_url` in `app.js` and `dashboard.js` if the API application port is different

3. Backend:
- Import the `emoWell_api` project into an IDE (e.g., IntelliJ, Eclipse, STS).
- Set up the MySQL database and configure the database connection in `application.properties`.
- Set the api-key (api.key) for ChatGPT api acess in `application.properties`
- Run the Spring Boot application.

## API Endpoints

- `POST /Emotion/add`: Add a new emotion entry for a specific date.
- `GET /Emotion/lineChart`: Retrieve line chart data.
- `GET /Emotion/findByDate`: Retrieve the emotion data for a specific date.
- `GET /Emotion/HappyPieChart`: Retrieve the top 10 happiness contributers.
- `GET /Emotion/SadPieChart`: Retrieve the top 10 unpleasant influences.

## Link for the Demo Video
https://drive.google.com/file/d/1oxech97DExAVi9s-EuLyJpf7AJFHQOc9/view?usp=sharing

## Future Enhancements

- User authentication and personalized profiles.
- Data analysis and insights.

## Contributor

- Nikita Tiwari (https://github.com/TiwariNikitaS)

