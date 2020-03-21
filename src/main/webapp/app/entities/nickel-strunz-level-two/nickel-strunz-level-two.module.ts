import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RockstockSharedModule } from 'app/shared/shared.module';
import { NickelStrunzLevelTwoComponent } from './nickel-strunz-level-two.component';
import { NickelStrunzLevelTwoDetailComponent } from './nickel-strunz-level-two-detail.component';
import { NickelStrunzLevelTwoUpdateComponent } from './nickel-strunz-level-two-update.component';
import { NickelStrunzLevelTwoDeleteDialogComponent } from './nickel-strunz-level-two-delete-dialog.component';
import { nickelStrunzLevelTwoRoute } from './nickel-strunz-level-two.route';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild(nickelStrunzLevelTwoRoute)],
  declarations: [
    NickelStrunzLevelTwoComponent,
    NickelStrunzLevelTwoDetailComponent,
    NickelStrunzLevelTwoUpdateComponent,
    NickelStrunzLevelTwoDeleteDialogComponent
  ],
  entryComponents: [NickelStrunzLevelTwoDeleteDialogComponent]
})
export class RockstockNickelStrunzLevelTwoModule {}
