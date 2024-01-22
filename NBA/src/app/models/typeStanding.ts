export type SingleTeamStanding = {
    team: {
        id: number;
        nbaFranchise: boolean;
        teamName: string;
        city: string;
        allStar: boolean;
        code: string;
        nickname: string;
        logo: string;
        conferenceName: string;
        divisionName: string;
    };
    season: number;
    conferenceRank: number;
    conferenceWin: number;
    conferenceLoss: number;
    divisionGamesBehind: number;
    homeWin: number;
    awayWin: number;
    totalWin: number;
    winPercentage: number;
    lastTenWin: number;
    homeLoss: number;
    awayLoss: number;
    totalLoss: number;
    lastTenLoss: number;
    gamesBehind: number;
};

export type Classifica = {
    allStanding: {
        eastConference: SingleTeamStanding[];
        westConference: SingleTeamStanding[];
    };
    favouriteStandings: {
        eastConference: SingleTeamStanding[];
        westConference: SingleTeamStanding[];
    };
};

export type StandingShow = {
    eastConference: SingleTeamStanding[];
    westConference: SingleTeamStanding[];
};
