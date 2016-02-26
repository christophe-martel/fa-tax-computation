
# Prerequisites
* JVM 1.8
* Maven 3
* A true shell (like bash)

# Documentation
## Functional
[Tax Computation](documents/readme.md)
## How to use
### Run the program
#### "La RACHE" method
Cf. http://www.byatoo.com/la-rache/
* `mvn exec:java`

#### Step by step
* Get sources
    * `git clone https://github.com/christophe-martel/fa-tax-computation.git`
* Go to good location
    * `cd fa-tax-computation`
* Prepare env
    * `mvn clean install`
* Run test
    * `mvn test`
* Prepare package
    * `mvn package`
* Run program
    * `mvn exec:java` or
    * `java -jar target/cma-fa-tc-1.3-full.jar`

# Help
If it fail, don't be afraid to use the green powder
Cf. http://www.poudreverte.org/


# History
### Version 1.3
* Use "~Either" pattern to manage respositories result

### Version 1.2
* Sysout from controller
* mvn exec:exec
* bug fixing (sorting of lines of order)

### Version 1.1
* First release
# Installation & test


