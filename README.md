# i18nboot - Spring Boot Internationalization (i18n) Project

A Spring Boot application demonstrating internationalization (i18n) implementation with multi-language support for greeting messages and error handling.

## Overview

This project showcases how to implement internationalization in a Spring Boot REST API, supporting multiple languages including English (default), German, French, and Japanese. The application provides localized messages based on the client's language preference.

## Features

- Multi-language support (English, German, French, Japanese)
- Locale-based message resolution
- Internationalized error messages
- RESTful API with i18n support
- Configurable message caching
- UTF-8 encoding for proper character display

## Project Structure

```
src/main/java/com/lokalise/i18nboot/
├── config/
│   └── MessageSourceConfig.java      # i18n configuration
├── controller/
│   ├── GreetingController.java       # REST endpoints
│   └── TestController.java
├── exception/
│   └── DemoException.java            # Custom exception with i18n keys
├── service/
│   └── GreetingService.java          # Business logic
└── I18nbootApplication.java          # Main application class

src/main/resources/
├── i18n/
│   ├── messages.properties           # Default (English) messages
│   ├── messages_de.properties        # German messages
│   ├── messages_fr.properties        # French messages
│   └── messages_ja.properties        # Japanese messages
└── application.properties
```

## Key Classes and Their Purposes

### 1. MessageSourceConfig
**Purpose**: Central configuration for internationalization
- Configures `ReloadableResourceBundleMessageSource` bean
- Sets message file locations (`classpath:i18n/messages`)
- Configures UTF-8 encoding for proper character display
- Sets cache duration (3600 seconds) for performance optimization
- Enables fallback to message keys when translations are missing

**Key Features**:
- **Message Caching**: Controls how often message files are reloaded from disk
- **Encoding Support**: UTF-8 encoding ensures proper display of non-English characters
- **Fallback Mechanism**: Returns the key itself if translation is missing

### 2. GreetingController
**Purpose**: REST API endpoints with internationalization support
- Handles HTTP requests for greeting functionality
- Resolves user locale from `Accept-Language` header
- Uses `MessageSource` to retrieve localized messages
- Supports parameterized messages with dynamic values

**Key Features**:
- Locale detection from HTTP headers
- Dynamic message parameter injection
- Internationalized error response handling

### 3. DemoException
**Purpose**: Custom exception class designed for i18n
- Stores message keys instead of hardcoded messages
- Supports parameterized error messages
- Enables localized error responses

**Key Features**:
- Message key-based error handling
- Support for dynamic parameters in error messages
- Integration with Spring's MessageSource

### 4. GreetingService
**Purpose**: Business logic with validation and i18n-aware error handling
- Validates user input (name and age)
- Throws `DemoException` with appropriate message keys
- Demonstrates how to integrate business logic with i18n

## Implementation Details

### Message Resolution Flow
1. Client sends request with `Accept-Language` header
2. Controller extracts locale information
3. `MessageSource` resolves appropriate message file based on locale
4. Messages are retrieved with parameter substitution
5. Localized response is returned to client

### Supported Locales
- **English (default)**: `messages.properties`
- **German**: `messages_de.properties`
- **French**: `messages_fr.properties`
- **Japanese**: `messages_ja.properties`

### Message File Format
```properties
greeting.welcome=Welcome, {0}!
greeting.age=You are {0} years old.
error.invalid.username=Username cannot be empty
error.invalid.age=Age must be between 18 and 120
```

## API Endpoints

### GET /api/greetings/{name}
Returns localized greeting messages.

**Parameters**:
- `name` (path): User's name
- `age` (query, default: 25): User's age
- `Accept-Language` (header): Preferred language (e.g., `de`, `fr`, `ja`)

**Example Requests**:
```bash
# English (default)
curl -X GET "http://localhost:8082/api/greetings/John?age=30"

# German
curl -X GET "http://localhost:8082/api/greetings/John?age=30" \
  -H "Accept-Language: de"

# French
curl -X GET "http://localhost:8082/api/greetings/John?age=30" \
  -H "Accept-Language: fr"
```

**Response Examples**:
```json
// English
{
  "message": "Welcome, John!",
  "ageInfo": "You are 30 years old."
}

// German
{
  "message": "Willkommen, John!",
  "ageInfo": "Sie sind 30 Jahre alt."
}

// French
{
  "message": "Bienvenue, John!",
  "ageInfo": "Vous avez 30 ans."
}
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Gradle 8.x

### Running the Application
```bash
# Clone the repository
git clone <repository-url>
cd i18nboot

# Run the application
./gradlew bootRun

# Or build and run the JAR
./gradlew build
java -jar build/libs/i18nboot-0.0.1-SNAPSHOT.jar
```

The application will start on port 8082.

### Testing Different Languages
```bash
# Test English (default)
curl "http://localhost:8082/api/greetings/Alice?age=25"

# Test German
curl "http://localhost:8082/api/greetings/Alice?age=25" -H "Accept-Language: de"

# Test French
curl "http://localhost:8082/api/greetings/Alice?age=25" -H "Accept-Language: fr"

# Test Japanese
curl "http://localhost:8082/api/greetings/Alice?age=25" -H "Accept-Language: ja"

# Test error handling (invalid age)
curl "http://localhost:8082/api/greetings/Alice?age=15" -H "Accept-Language: de"
```

## Configuration

### Application Properties
```properties
spring.application.name=i18nboot
spring.web.locale=en_US
server.port=8082
```

### Message Source Configuration
- **Cache Duration**: 3600 seconds (1 hour)
- **Encoding**: UTF-8
- **Base Location**: `classpath:i18n/messages`
- **Fallback**: Uses message key if translation missing

## Adding New Languages

1. Create a new properties file: `src/main/resources/i18n/messages_{locale}.properties`
2. Add translations for all existing keys
3. Restart the application
4. Test with appropriate `Accept-Language` header

Example for Spanish (`messages_es.properties`):
```properties
greeting.welcome=¡Bienvenido, {0}!
greeting.age=Tienes {0} años.
error.invalid.username=El nombre de usuario no puede estar vacío
error.invalid.age=La edad debe estar entre 18 y 120
```

## Technologies Used

- **Spring Boot 3.5.9**: Main framework
- **Spring Web**: REST API support
- **Java 17**: Programming language
- **Gradle**: Build tool
- **Spring REST Docs**: API documentation
- **JUnit 5**: Testing framework

## Best Practices Demonstrated

1. **Separation of Concerns**: Configuration, controllers, services, and exceptions are properly separated
2. **Message Key Strategy**: Using descriptive keys like `greeting.welcome` instead of hardcoded messages
3. **Parameter Support**: Dynamic message content using `{0}`, `{1}` placeholders
4. **Fallback Mechanism**: Graceful handling of missing translations
5. **UTF-8 Encoding**: Proper support for international characters
6. **Caching Strategy**: Performance optimization through message caching
7. **Error Internationalization**: Localized error messages for better user experience