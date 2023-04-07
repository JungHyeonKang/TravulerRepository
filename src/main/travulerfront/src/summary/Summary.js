import '../summary/summary.css'

const Summary = ({columns, tPlanData, columnsHit, hitPlaceData}) => {

    return (
        <div className="Summary">
            <h1>최근 인기 여행지</h1>
            <div className="table-wrapper">
                <table className="table">
                    <thead>
                        <tr>
                        {columnsHit.map((column, idx) => (
                            <th key={column + idx}>{column}</th>
                        ))}
                        </tr>
                    </thead>
                    <tbody>
                        {hitPlaceData.map((hitPlace, idx) => (
                            <tr key={hitPlace.name + idx}>
                                <td>{hitPlace.name}</td>
                                <td>{hitPlace.hitCnt}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <h1>나의 여행 일정</h1>
            <div className="table-wrapper">
                <table className="table">
                    <thead>
                        <tr>
                        {columns.map((column, idx) => (
                            <th key={column + idx}>{column}</th>
                        ))}
                        </tr>
                    </thead>
                    <tbody>
                        {tPlanData.map((plan, idx) => (
                            <tr key={plan.planName + idx}>
                                <td>{plan.planName}</td>
                                <td>{plan.st_dt}</td>
                                <td>{plan.end_dt}</td>
                                <td>{plan.root}</td>
                                <td>{plan.shareStatus == "N"? <button className="button green">공유하기</button> : <span>공유완료</span>}</td>
                                <td><button className="button gray">수정하기</button></td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Summary;