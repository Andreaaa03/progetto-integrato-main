export type Standing = {
    response: {
        league: string;
        season: number;
        team: {
            id: number;
            name: string;
            nickname: string;
            code: string;
            logo: string;
        };
        conference: {
            name: string;
            rank: number;
            win: number;
            loss: number;
        };
        division: {
            name: string;
            rank: number;
            win: number;
            loss: number;
            gamesBehind: null;
        };
        win: {
            home: number;
            away: number;
            total: number;
            percentage: string;
            lastTen: number;
        };
        loss: {
            home: number;
            away: number;
            total: number;
            percentage: string;
            lastTen: number;
        };
        gamesBehind: null;
        streak: number;
        winStreak: true;
        tieBreakerPoints: null;
        preferiti: boolean;
    }[];
};

export type SingleTeamStanding = {
    league: string;
    season: number;
    team: {
        id: number;
        name: string;
        nickname: string;
        code: string;
        logo: string;
    };
    conference: {
        name: string;
        rank: number;
        win: number;
        loss: number;
    };
    division: {
        name: string;
        rank: number;
        win: number;
        loss: number;
        gamesBehind: null;
    };
    win: {
        home: number;
        away: number;
        total: number;
        percentage: number;
        lastTen: number;
    };
    loss: {
        home: number;
        away: number;
        total: number;
        percentage: string;
        lastTen: number;
    };
    gamesBehind: null;
    streak: number;
    winStreak: true;
    tieBreakerPoints: null;
    preferiti: boolean;
};

export type Classifica = {
    allStanding: {
        eastConference: SingleTeamStanding[],
        westConference: SingleTeamStanding[],
    },
    favouriteStandings: {
        eastConference: SingleTeamStanding[],
        westConference: SingleTeamStanding[],
    }
}

export type StandingShow = {
    eastConference: SingleTeamStanding[],
    westConference: SingleTeamStanding[],
}