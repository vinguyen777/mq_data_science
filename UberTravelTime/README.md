## Predicting Travel Times of Uber by Analysing the Effects of Weather Conditions and Traffic Density

### Group 7 Members:   

* Chi Thanh Liu
* Hieu Vi Nguyen
* Huong Uyen Duong
* Thi Thu Huong Nguyen

### Summary:
This project will consider changing the travel time for Uber in 2019 from Sydney Airport to Circular Quay. In the project, we will look at which months had the highest travel times in 2019. Furthermore, this project will use weather factors such as temperature and precipitation of 2019 to find out whether it will affect Uber's travel times. Not only that, but we also check whether the amount of vehicles travelling on the M1 road affects the ride time of Uber or not. The reason we chose the data on the number of cars commuting in M1 is that M1 is possibly the fastest route to get from Sydney Airport to Circular Quay. We think that Uber drivers will typically use the fastest routes to get passengers to their destination. Therefore, we want to see if Uber drivers use the M1 route that has any effect on their travel times.

### Datasets:

* Uber's Travel Time: <https://movement.uber.com/?lang=en-AU>   
* Weather conditions such as Temperature and Rainfall: <http://www.bom.gov.au/>   
* M1 Route Data: <https://nswtollroaddata.com/data-download/?fbclid=IwAR2JnpLC4v4wFjod4yNH6KrdPY0jQ7rZ_xOM49c0_zr5Z5eOVurWwhmszJk>   

### The methods are used in the project and the results obtained through each method:

**Data Visualization:**   We have created respectively three plots showing Uber's average daily travel times for the whole year (2019), the month with the highest Uber daily travel times, and the month with the average daily travel time of Uber is the lowest.   

*As a result of these plots, Uber's average daily travel time fluctuates sharply from the beginning of the year to the end of the year. In which, May has the highest average daily travel time on May 7th with nearly 1500 seconds. In contrast, October has the lowest average daily moving time of the year, falling on two days, October 6th and October 12th, with seconds below 800.*

**Regression Model:** We have trained the model in two cases. We trained the model in two cases. The first case is to use two features which are "Maximum temperature (Degree C)" and "Rainfall amount (millimetres)". The second case is the number of features increased to three which are "Maximum temperature (Degree C)", '' Rainfall amount (millimetres) ", and" TotalVolume ". The model in both cases is tested on both the training suite and the testing suite to calculate RMSE, R Squared, MAE, and MAPE.

*As a result, training the model to use three features is much better than using only two. The square of R when testing a model with three features on the training set is 0.52 and on the test set is 0.61.*

**Correlation:** We have examined the correlations between the features.

*As a result, There is a strong uphill (positive) linear relationship between total volumn and daily mean travel time (0.73). Unexpectedly, there is no observed relationship between rainfall amount and travel time due to an extremely low correlation coefficient of nearly 0. Rainfall amount from Sydney station and airport station have a high correlation of 0.88. However, it also shows that the data from 2 stations is not exactly the same, which means they can serve as 2 variables for a better result.*

**Logistic Regression and K-Neighbor Classifier:** Since the linear model does not give good results and it is difficult to accurately predict travel time based on only 3 or 4 variables, we decided instead to predict the fast, normal and slow travel time. In this case, we used Logistic Regression as well as K-Neighbor Classifier to compare the accuracy from these two classification methods when using to classify Uber's an average daily travel time whether is quick, moderate or slow.

*From the result, Both models have moderate accuracy score and give much better results than linear regression. As we can see, K-neighbor classifier (0.69) has higher accuracy score than Logistic Regression (0.66).*     

### Project Conclusion:

Uber's travel time in one year (2019) has significant fluctuations. The average daily travel time peaked around May, with more than 1,400 seconds. Meanwhile, Uber's average daily travel time hit a low in October, with seconds below 800. 

Total traffic volumn appears to affect daily travel time the most; meanwhile, rainfall amount unexpectedly has a considerably low correlation with travel time. 

To predict exactly travel time, the more features was used, the less overfitting the model becomes. However, linear model still shows its ineffectiveness.

After classifying travel time into 3 levels, we can see that Logistic Regression and K-Neighbor Classifier are very effective in predicting travel time level, with accuracy scores of 0.66 and 0.69, respectively. In terms of Logistic Regression, the model shows a significantly low RMSE score when using 4 features, comparing to the Linear regression model. 

**In the future, we can improve our models by using more travel time data from previous years and exploring more factors that can affect travel time. However, the latter can face a limitation due to the scarcity of available online data.**
