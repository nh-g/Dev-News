# Dev News

## Introduction
This project is a news site for developers built by Spring Web framework.
Users can make CRUD requests and get plain json text responses via curl/Postman.

## Dependencies
The project uses these dependencies:
* Spring Web
* Spring JPA
* PostgreSQL Driver

## Setup
###Docker
`docker-compose up`
###Gradle
On MacOS or Linux
`./gradlew bootRun`
On Windows
`gradle bootRun`

####Postman
A sample collection of CRUD requests can be found here:
`https://www.getpostman.com/collections/575d30b03c3dad39230d`

## Using the API

### Article
Article is the core entity. It represents a news article with an:
* id
* title
* body (article text content)
* authorName

#### Endpoints

| HTTP Method | HTTP Path | Action |
| ------------|-----------|--------|
| `GET`    | `/articles`      | return all articles. |
| `GET`    | `/articles/{id}` | return a specific article based on the provided id.|
| `POST`   | `/articles`      | create a new article.|
| `PUT`    | `/articles/{id}` | update the given article.|
| `DELETE` | `/articles/{id}` | delete the given article.|

### Comments
Users can comment the different articles with a unique **id**, **body**, **authorName** (for the comment), and **article**
on which the comment was posted. Each article can have zero or more comments.

Example JSON response when requesting a comment:

```json
{
    "id": 1,
    "body": "This article is very well written",
    "authorName": "John Smith",
    "article": {
        "id": 1,
        "title": "10 reasons to learn Spring",
        "body": "In this article I'll be listing 10 reasons why you should learn spring and use it in your next project...",
        "authorName": "John Smith"
    }
}

```
#### Endpoints

| HTTP Method | HTTP Path | Action |
| ------------|-----------|--------|
| `GET`    | `/articles/{articleId}/comments`    | return all comments on article given by `articleId`. |
| `GET`    | `/comments?authorName={authorName}` | return all comments made by author given by `authorName`. |
| `POST`   | `/articles/{articleId}/comments`    | create a new comment on article given by `articleId`. |
| `PUT`    | `/comments/{id}`                    | update the given comment. |
| `DELETE` | `/comments/{id}`                    | delete the given comment. |


### Topics
Articles can be catagorized by topics. Each topic can be applied to zero or many articles and each article can have zero or many topics.

Example JSON response when requesting an article:

```json
{
    "id": 1,
    "title": "10 reasons to learn Spring",
    "body": "In this article I'll be listing 10 reasons why you should learn spring and use it in your next project...",
    "authorName": "John Smith",
    "topics": [
        {
            "id": 1,
            "name": "backend"
        },
        {
            "id": 2,
            "name": "java"
        },
        {
            "id": 3,
            "name": "spring"
        }
    ]
}
```

#### Endpoints

| HTTP Method | HTTP Path | Action |
| ------------|-----------|--------|
| `GET`    | `/topics` | return all topics. |
| `GET`    | `/articles/{articleId}/topics` | return all topics associated with article given by `articleId`. |
| `POST`   | `/articles/{articleId}/topics` | associate the topic with the article given by `articleId`. If topic does not already exist, it is created. |
| `POST`   | `/topics` | create a new topic. |
| `PUT`    | `/topics/{id}` | update the given topic. |
| `DELETE` | `/topics/{id}` | delete the given topic. |
| `DELETE` | `/articles/{articleId}/topics/{topicId}` | delete the association of a topic for the given article. The topic & article themselves remain. |
| `GET`    | `/topics/{topicId}/articles` | return all articles associated with the topic given by `topicId`. |

## Timeline
Created between Apr 7-13 2021 for an assignment from Software Development Academy, Backend Module.