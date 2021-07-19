import './App.css';

import React from 'react';
import {Chart,Ticks, Bars, Lines, Title} from 'rumble-charts';

var server = "http://localhost:8080";
var AppContext = React.createContext();

function makeAxis(dir,color) {
    return <Ticks axis={dir}  className={"tick"+dir}   lineLength="100%" lineVisible={true} tickVisible={true} lineStyle={{stroke: color}} />; 
}

function DynamicChart(props) {

    var {type, heading ,width, height, series, linePadding, ...others} = props;

    var xAxis = makeAxis('x','gold',);
    var yAxis = makeAxis('y','gold');

    var graph;
    if(type == 'Bars') {
        graph = <Bars linePadding={linePadding} {...others}  />
    } else if(type == 'Lines') {
        graph = <Lines {...others} />
    } else {
        graph = <p>Unknown graph type</p>;
    }


    var title = <Title position = "middle middle" className="heading" style={{textAnchor: 'middle'}}>{heading}</Title>

    return <Chart className="chart" width={width} height={height} series={series} minY={0} >
        {graph}
        {title}
        {xAxis}
        {yAxis}
        <Title position =  {[width, height+15]} >{props.xLabel}</Title>
        <Title position = {[20, 0]} style={{textAnchor: 'start'}}>{props.yLabel}</Title>
    </Chart>
}

var series = [
    {
        data: Array.from({length: 10}, (o,i)=>{
            return {x:i, y:Math.floor(Math.random() * 100), color: 'mediumseagreen'};
        }),
        color: 'firebrick'
    }
];

class ErrorBoundary extends React.Component {

    constructor(props) {
        super(props);
        this.state = {hasError: false};
    }

    static getDerivedStateFromError(error) {
        return {hasError: true};
    }

    componentDidCatch(error, errorInfo) {
        console.error(error);
    }


    render() {
        return this.state.hasError?<p>Error caught</p>:this.props.children;
    }
}


function GraphArea(props) {

    let context = React.useContext(AppContext);

    let [graphData, setGraphData] = React.useState(undefined);

    React.useEffect(()=>{
        fetch(server+'/rest/companies/details').then(res=>res.json()).then(res=>{
            console.log(res);
            setGraphData(res);
        }) 
    }, [context.username]);

    if(!graphData) return <p></p>;

    let comp = graphData[0];
    let measure = Object.keys(comp.performance)[0];
    let series = Object.entries(comp.performance[measure]).map(pair=>[+pair[0],pair[1]]);
    series = [{data:series}];
    console.log(series);

    return <React.Fragment>
        <DynamicChart type="Bars" width={500} height={200} heading={comp.name} series={series} xLabel="years" yLabel={measure}  linePadding={10} />        
    </React.Fragment>

}

class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {email:'', password:''};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
    }

    handleSubmit(evt,context) {
        evt.preventDefault();
        let body = JSON.stringify({email:this.state.email, password: this.state.password});
        var headers = new Headers({'Content-Type':'application/json','Accept':'text/plain'});
        let req = new Request(server+'/login', {method:'POST',body,headers});
        fetch(req).then((token)=>{
            console.log(token);
            if(token) {
                context.updateCredentials(token, this.state.email);
            }
        })
    }

    handleEmailChange(evt) {
        this.setState({'email':evt.target.value});
    }

    handlePasswordChange(evt) {
        this.setState({password: evt.target.value});
    }


    render() {

        return <AppContext.Consumer>
            {(context)=>{
                console.log(context);
            return <form className="login-form" onSubmit={(evt)=>{this.handleSubmit(evt,context)}} >
                <p>Email : <input type="email" placeholder="user@example.com" onChange={this.handleEmailChange} required/></p>
                <p>Password : <input type="password" onChange={this.handlePasswordChange} required/></p>
                <button type="submit">Log in</button>
            </form>}}
        </AppContext.Consumer>;
    }
}


function App() {

    let appInformation = {
        token:'',
        username:'',
        updateCredentials:(token,username)=>{
            sessionStorage.setItem('token',token);
            sessionStorage.setItem('username',username);
            setAppInfo({token,username});
        },
        logout:()=>{
            sessionStorage.setItem('token','');
            sessionStorage.setItem('username','');
            setAppInfo({token:'',username:''});
        } 
    };

    let [appInfo,setAppInfo] = React.useState(appInformation) 

    React.useEffect(()=>{
        let token = sessionStorage.getItem('token');
        let username = sessionStorage.getItem('username');
        if(token && username) {
            appInfo.updateCredentials(token,username )
        }
    },[])


    return (
        <div className="app">
            <ErrorBoundary>
                <AppContext.Provider value={appInfo}>
                    <header>
                        <h1>Key Performance Indicators</h1>
                        <h4>Welcome {appInfo.username?appInfo.username:'Guest'}</h4>
                    </header>
                    {!appInfo.username && <LoginForm />}
                    {appInfo.username && <GraphArea username={appInfo.username} /> }
                </AppContext.Provider>
            </ErrorBoundary>
        </div>
    );
}

export default App;
