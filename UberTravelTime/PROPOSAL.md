## COMP2200 Data Science

# Project Proposal

## PREDICTING TRAVEL TIMES BY ANALYSING THE EFFECTS OF WEATHER CONDITIONS AND TRAFFIC DENSITY

![Sydneyroad](https://i.ytimg.com/vi/prCJRlfPQng/maxresdefault.jpg)


### Project Summary:

This project attempts to predict the Uber trip duration by exploring the impacts
of weather conditions and traffic volumes (the number of vehicles passing a
specific segment of a road). The primary objective is to investigate whether or
not meteorological conditions and traffic intensities uniformly alter the Uber
trip duration in Sydney in order to forecast its travel time. For our analysis,
ride data has been collected from Uber movement website from January 2019 to
December 2019, and weather information has been collected from Australian Bureau
of Meteorology for the same period of twelve months from January 2019 to
December 2019. For the purpose of this study, the origin of all Uber trips was
set at Sydney Airport, while Circular Quay was defined as the destination
location.

### Project Objectives:

With respect to the Uber movement data, this project was motivated to predict
the mean travel time of Uber services and identify its major determinants by
examining the association of meteorological conditions and traffic intensities
with the duration of Uber trips in Sydney (from Sydney Airport to Circular
Quay), through the use of locative digital data.

### Summary of the datasets:

The dataset our group will use in the analysis is the Travel Times Daily dataset
from Uber. We got the dataset from the Uber movement website. The format of the
data is in CSV. The data is related to the time of Uber when they ride their
customers from A position to B position in 2019. Because the data is extensive
if we try to get all the data from different destinations, we decide to start
from Sydney Airport to Circular Quay.

The link to get the dataset: <https://movement.uber.com/?lang=en-AU>

Our group also used the datasets from Australian Bureau of Meteorology to get
the temperature and rainfall datasets at Sydney in 2019. The format of these
datasets is CSV. The reason our group gets these datasets is to find whether
these factors would impact on the moving time of Uber or not. The last dataset
we intend to use is from NSW Toll Road Data. The format of the dataset is CSV.
This dataset describes the number of vehicles moving on the M1 route in 2019,
the reason why we use this dataset is also to check whether the data impact on
the moving time of Uber.

The link to get the datasets (Australian Bureau of Meteorology):
<http://www.bom.gov.au/>

The link to get the dataset (NSW Toll Road Data):
<https://nswtollroaddata.com/data-download/?fbclid=IwAR2JnpLC4v4wFjod4yNH6KrdPY0jQ7rZ_xOM49c0_zr5Z5eOVurWwhmszJk>

### Techniques:

Regression (Simple Linear): To predict the moving time based on temperature and
rainfall.

Time series analysis: Observing the moving time overtime.

RFE: To select which feature is better to predict.

Histogram: To observe the distribution of a dataset.

There might be other techniques which are appropriate with our project that we
have not learnt yet. In the learning process, we would consider more techniques
which are suitable to use in our project.

### Project Milestones:

Milestone \#1: Data preparation for analysis - Week 8

-   Retrieve data via the websites

-   Join all the data into completed datasets

-   Data cleansing

Milestone \#2: Project notebook - Week 11

-   Complete all the intended analysis

-   Data visualization

    **(Complete all the tasks)**

Milestone \#3: Project video presentation - Week 12

-   Make a video to present the group project
